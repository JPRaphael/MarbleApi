package com.jpraphael.marble.services;

import com.jpraphael.marble.VO.UsuarioVO;
import com.jpraphael.marble.model.Sessao;
import com.jpraphael.marble.model.Usuario;
import com.jpraphael.marble.model.UsuarioContext;
import com.jpraphael.marble.repository.UsuarioRepository;
import com.jpraphael.marble.utils.GenericDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UsuarioService extends AbstractCrudService<Usuario> {

    @Inject
    private GenericDao<Usuario> repository;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private UsuarioContext usuarioContext;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Usuario save(Usuario usuario) {

        usuario.setSenha(this.passwordSecure(usuario.getSenha()));

        return repository.save(usuario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UsuarioVO update(Usuario usuario) {

        Usuario usuarioContexto = this.usuarioContexto();

        String senha = usuario.getSenha();
        if (senha != null) {
            usuarioContexto.setSenha(this.passwordSecure(senha));
        }

        usuarioContexto.setNome(usuario.getNome());
        usuarioContexto.setUsuario(usuario.getUsuario());
        usuarioContexto.setEmail(usuario.getEmail());

        return new UsuarioVO(repository.save(usuarioContexto));

    }

    public UsuarioVO eu() {

        final String usuario = usuarioContext.getUsuario();
        final Usuario byUsuario = usuarioRepository.findByUsuario(usuario);

        return new UsuarioVO(byUsuario);
    }

    public Usuario usuarioContexto() {

        final String usuario = usuarioContext.getUsuario();

        return usuarioRepository.findByUsuario(usuario);

    }

    public Sessao logar(Usuario usuario) throws LoginException {

        String passwordSecure = this.passwordSecure(usuario.getSenha());

        Usuario byUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

        if (byUsuario == null) {
            throw new LoginException("Usuário " + usuario.getUsuario() + " não encontrado");
        }

        if (passwordSecure.equals(byUsuario.getSenha())) {
            return new Sessao(byUsuario, this.generateToken(usuario.getUsuario()));
        }

        throw new LoginException("Senha incorreta");

    }

    public String generateToken(String usuario) {
        SecretKey secretKey = Keys.hmacShaKeyFor("MInhaMErdaDeChaveSecretaDosInferno".getBytes());
        String token = Jwts.builder().setSubject(usuario)
                .setIssuedAt(new Date())
                .setExpiration(
                        Date.from(LocalDateTime.now()
                                .plusMinutes(60L)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()))
                .signWith(secretKey)
                .compact();
        return "Bearer ".concat(token);
    }

    private String passwordSecure(String senha) {

        MessageDigest md5;
        StringBuilder sb = new StringBuilder();
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(senha.getBytes());
            for (byte aByte : md5.digest()) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return sb.toString();
    }

    @Override
    protected GenericDao<Usuario> getDao() {
        return null;
    }
}
