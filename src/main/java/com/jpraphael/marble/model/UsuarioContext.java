package com.jpraphael.marble.model;

import javax.enterprise.context.RequestScoped;


@RequestScoped
public class UsuarioContext {

    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
