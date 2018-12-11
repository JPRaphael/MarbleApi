package com.jpraphael.marble.rest.resources;

import com.jpraphael.marble.model.Usuario;
import com.jpraphael.marble.services.UsuarioService;
import com.jpraphael.marble.utils.RequestAuth;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @POST
    @Path("login")
    public Response login(Usuario usuario) throws LoginException {

        return Response.ok(usuarioService.logar(usuario)).build();

    }

    @POST
    public Response create(Usuario usuario) {

        return Response.ok(usuarioService.save(usuario)).build();

    }

    @PUT
    @RequestAuth
    public Response update(Usuario usuario) {

        return Response.ok(usuarioService.update(usuario)).build();

    }

    @GET
    @Path("/eu")
    @RequestAuth
    public Response findOne() {

        return Response.ok(usuarioService.eu()).build();

    }
}
