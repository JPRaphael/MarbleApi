package com.jpraphael.marble.rest;

import com.jpraphael.marble.model.Entidade;
import com.jpraphael.marble.services.AbstractCrudService;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public abstract class AbstractCrudResource<T extends Entidade> {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(
            @QueryParam("page")
            @DefaultValue("1") Integer pageNumber,
            @QueryParam("size")
            @DefaultValue("15") Integer pageSize,
            @QueryParam("filterField") String filterField,
            @QueryParam("filterValue") String filterValue,
            @QueryParam("order") String order) {

        Long total = getService().getCount(filterField, filterValue);
        Response.Status responseStatus = (pageNumber * pageSize < total) ? Response.Status.PARTIAL_CONTENT : Response.Status.OK;
        Response response = Response.status(responseStatus)
                .entity(getService().findAll(pageSize, pageNumber, filterField, filterValue, order)).build();
        response.getHeaders().add("X-Total-Lenght", total);
        response.getHeaders().add("X-Page-Size", pageSize);
        response.getHeaders().add("X-Current-Page", pageNumber);
        return response;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> findAllOver(
            @QueryParam("filterField") String filterField,
            @QueryParam("filterValue") String filterValue,
            @QueryParam("order") String order) {
        return getService().findAllOver(filterField, filterValue, order);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public T findById(@PathParam("id") Long id) {
        return getService().findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(T entity) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(getService().insert(entity))
                    .build();
        } catch (Throwable e) {
            ConstraintViolationException ex = catchException(e);
            if (ex != null)
                throw ex;
            throw e;
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T entity, @PathParam("id") Long id) {
        try {
            if (!id.equals(entity.getId())) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("ID do objeto difere do ID da URL")
                        .build();
            }
            return Response.status(Response.Status.OK)
                    .entity(getService().insert(entity))
                    .build();
        } catch (Throwable e) {
            ConstraintViolationException ex = catchException(e);
            if (ex != null)
                throw ex;
            throw e;
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        getService().remove(id);
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    private ConstraintViolationException catchException(Throwable ex) {
        if (ex.getCause() == null)
            return null;
        if (ex instanceof ConstraintViolationException)
            return (ConstraintViolationException) ex;
        return catchException(ex.getCause());
    }

    protected abstract AbstractCrudService<T> getService();
}
