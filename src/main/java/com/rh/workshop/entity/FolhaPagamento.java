package com.rh.workshop.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "folha_pagamento")
public class FolhaPagamento extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    public Funcionario funcionario;

    @Column(name = "mes_referencia", nullable = false)
    public LocalDate mesReferencia;

    @Column(name = "salario_bruto", nullable = false, precision = 10, scale = 2)
    public BigDecimal salarioBruto;

    @Column(name = "desconto_inss", nullable = false, precision = 10, scale = 2)
    public BigDecimal descontoInss;

    @Column(name = "desconto_irrf", nullable = false, precision = 10, scale = 2)
    public BigDecimal descontoIrrf;

    @Column(name = "desconto_vale_transporte", nullable = false, precision = 10, scale = 2)
    public BigDecimal descontoValeTransporte;

    @Column(name = "desconto_plano_saude", nullable = false, precision = 10, scale = 2)
    public BigDecimal descontoPlanoSaude;

    @Column(name = "salario_liquido", nullable = false, precision = 10, scale = 2)
    public BigDecimal salarioLiquido;
}
