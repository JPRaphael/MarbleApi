package com.jpraphael.marble.utils;

import com.jpraphael.marble.model.UsuarioContext;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.annotation.Priority;
import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@RequestAuth
@Priority(Priorities.AUTHENTICATION)
public class RequestAuthFilter implements ContainerRequestFilter {

    @Inject
    private UsuarioContext usuarioContext;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {

        String authHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authHeader.substring("Bearer".length()).trim();

        try {

            SecretKey secretKey = Keys.hmacShaKeyFor("MInhaMErdaDeChaveSecretaDosInferno".getBytes());
            String subject = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            usuarioContext.setUsuario(subject);


        } catch (Exception e) {

            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

        }

    }

}
