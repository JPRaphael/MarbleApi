package com.jpraphael.marble.utils;

import com.jpraphael.marble.model.UsuarioContext;
import com.jpraphael.marble.services.UsuarioService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
@RequestAuth
@Priority(Priorities.AUTHENTICATION)
public class ResponseAuthFilter implements ContainerResponseFilter {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private UsuarioContext usuarioContext;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("Authorization", usuarioService.generateToken(usuarioContext.getUsuario()));
    }

}

