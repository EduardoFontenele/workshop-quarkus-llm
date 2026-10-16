package com.rh.workshop.service;

import com.rh.workshop.entity.FolhaPagamento;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FolhaPagamentoService {

    public List<FolhaPagamento> buscarPorFuncionario(Long funcionarioId) {
        return FolhaPagamento.find("funcionario.id = ?1 order by mesReferencia", funcionarioId).list();
    }
}
