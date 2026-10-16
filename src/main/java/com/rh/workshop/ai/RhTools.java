package com.rh.workshop.ai;

import com.rh.workshop.dto.FolhaPagamentoResponse;
import com.rh.workshop.entity.Funcionario;
import com.rh.workshop.service.FolhaPagamentoService;
import com.rh.workshop.service.FuncionarioService;
import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * Tools expostas ao LLM via tool calling. Reaproveitam os mesmos services
 * usados pela API REST clássica, para que ambas as camadas compartilhem a
 * mesma lógica de acesso a dados.
 */
@ApplicationScoped
public class RhTools {

    @Inject
    FuncionarioService funcionarioService;

    @Inject
    FolhaPagamentoService folhaPagamentoService;

    @Tool("Lista todos os funcionários cadastrados no departamento de RH, com id, nome, cpf, email, cargo, departamento, data de admissão e telefone.")
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarTodos();
    }

    @Tool("Busca os dados cadastrais de um funcionário específico a partir do seu ID. Retorna null se o funcionário não existir.")
    public Funcionario buscarFuncionarioPorId(Long funcionarioId) {
        return funcionarioService.buscarPorId(funcionarioId);
    }

    @Tool("Busca o histórico de folha de pagamento de um funcionário (salário bruto, descontos de INSS, IRRF, vale-transporte, plano de saúde e salário líquido) a partir do ID dele. Retorna uma lista vazia se não houver registros.")
    public List<FolhaPagamentoResponse> buscarFolhaPagamentoPorFuncionario(Long funcionarioId) {
        return folhaPagamentoService.buscarPorFuncionario(funcionarioId)
                .stream()
                .map(FolhaPagamentoResponse::from)
                .toList();
    }
}
