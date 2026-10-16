package com.rh.workshop.dto;

import com.rh.workshop.entity.FolhaPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FolhaPagamentoResponse {

    public Long id;
    public Long funcionarioId;
    public LocalDate mesReferencia;
    public BigDecimal salarioBruto;
    public BigDecimal descontoInss;
    public BigDecimal descontoIrrf;
    public BigDecimal descontoValeTransporte;
    public BigDecimal descontoPlanoSaude;
    public BigDecimal salarioLiquido;

    public static FolhaPagamentoResponse from(FolhaPagamento folha) {
        FolhaPagamentoResponse response = new FolhaPagamentoResponse();
        response.id = folha.id;
        response.funcionarioId = folha.funcionario.id;
        response.mesReferencia = folha.mesReferencia;
        response.salarioBruto = folha.salarioBruto;
        response.descontoInss = folha.descontoInss;
        response.descontoIrrf = folha.descontoIrrf;
        response.descontoValeTransporte = folha.descontoValeTransporte;
        response.descontoPlanoSaude = folha.descontoPlanoSaude;
        response.salarioLiquido = folha.salarioLiquido;
        return response;
    }
}
