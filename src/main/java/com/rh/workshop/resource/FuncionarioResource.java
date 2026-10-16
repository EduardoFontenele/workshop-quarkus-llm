package com.rh.workshop.resource;

import com.rh.workshop.dto.FuncionarioRequest;
import com.rh.workshop.entity.Funcionario;
import com.rh.workshop.service.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    @Inject
    FuncionarioService funcionarioService;

    @GET
    public List<Funcionario> listarTodos() {
        return funcionarioService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        if (funcionario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Funcionário com id " + id + " não encontrado")
                    .build();
        }
        return Response.ok(funcionario).build();
    }

    @POST
    public Response criar(FuncionarioRequest request) {
        Funcionario funcionario = funcionarioService.criar(request);
        return Response.status(Response.Status.CREATED).entity(funcionario).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, FuncionarioRequest request) {
        Funcionario funcionario = funcionarioService.atualizar(id, request);
        if (funcionario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Funcionário com id " + id + " não encontrado")
                    .build();
        }
        return Response.ok(funcionario).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        boolean excluido = funcionarioService.excluir(id);
        if (!excluido) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Funcionário com id " + id + " não encontrado")
                    .build();
        }
        return Response.noContent().build();
    }
}
