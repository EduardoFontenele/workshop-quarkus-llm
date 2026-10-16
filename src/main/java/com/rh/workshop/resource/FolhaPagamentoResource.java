package com.rh.workshop.resource;

import com.rh.workshop.dto.FolhaPagamentoResponse;
import com.rh.workshop.entity.Funcionario;
import com.rh.workshop.service.FolhaPagamentoService;
import com.rh.workshop.service.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/funcionarios/{funcionarioId}/folha-pagamento")
@Produces(MediaType.APPLICATION_JSON)
public class FolhaPagamentoResource {

    @Inject
    FuncionarioService funcionarioService;

    @Inject
    FolhaPagamentoService folhaPagamentoService;

    @GET
    public Response buscarPorFuncionario(@PathParam("funcionarioId") Long funcionarioId) {
        Funcionario funcionario = funcionarioService.buscarPorId(funcionarioId);
        if (funcionario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Funcionário com id " + funcionarioId + " não encontrado")
                    .build();
        }

        List<FolhaPagamentoResponse> folhas = folhaPagamentoService.buscarPorFuncionario(funcionarioId)
                .stream()
                .map(FolhaPagamentoResponse::from)
                .toList();

        return Response.ok(folhas).build();
    }
}
