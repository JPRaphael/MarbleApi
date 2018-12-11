package com.jpraphael.marble.rest.resources;

import com.jpraphael.marble.viewmodel.OrcamentoREL;
import com.jpraphael.marble.model.ViewOrcamento;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("view-orcamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ViewOrcamentoResource {

    @POST
    public Response insert(List<ViewOrcamento> listViewOrcamento, @QueryParam("nomeArquivo") String nomeArquivo) {
        OrcamentoREL relatorio = new OrcamentoREL();
        try {
            relatorio.imprimir(listViewOrcamento, nomeArquivo);
        } catch (Exception e) {
//            throw new Exception();
        }
        return Response.ok().build();
    }

    @GET
    public Response findAll() {
        return Response.ok().build();
    }
}
