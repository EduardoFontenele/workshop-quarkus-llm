package com.rh.workshop.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 120)
    public String nome;

    @Column(nullable = false, unique = true, length = 14)
    public String cpf;

    @Column(nullable = false, unique = true, length = 120)
    public String email;

    @Column(nullable = false, length = 80)
    public String cargo;

    @Column(nullable = false, length = 80)
    public String departamento;

    @Column(name = "data_admissao", nullable = false)
    public LocalDate dataAdmissao;

    @Column(length = 20)
    public String telefone;
}
