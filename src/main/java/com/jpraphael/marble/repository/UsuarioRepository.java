package com.jpraphael.marble.repository;

import com.jpraphael.marble.model.Usuario;
import com.jpraphael.marble.utils.GenericDao;

import javax.inject.Inject;

import static com.jpraphael.marble.model.QUsuario.usuario1;

public class UsuarioRepository {

    @Inject
    private GenericDao<Usuario> repository;

    public Usuario findByUsuario(String usuario) {

        return repository.query()
                .selectFrom(usuario1)
                .from(usuario1)
                .where(usuario1.usuario.eq(usuario))
                .fetchOne();

    }

}
