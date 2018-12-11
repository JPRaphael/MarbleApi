package com.jpraphael.marble.VO;

import com.jpraphael.marble.model.Usuario;

public class UsuarioVO extends Usuario {

    private Long id;
    private String usuario;
    private String nome;
    private String email;

    public UsuarioVO(Usuario usuario) {
        this.id = usuario.getId();
        this.usuario = usuario.getUsuario();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
