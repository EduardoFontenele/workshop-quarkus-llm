package com.rh.workshop.service;

import com.rh.workshop.dto.FuncionarioRequest;
import com.rh.workshop.entity.Funcionario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FuncionarioService {

    public List<Funcionario> listarTodos() {
        return Funcionario.listAll();
    }

    public Funcionario buscarPorId(Long id) {
        return Funcionario.findById(id);
    }

    @Transactional
    public Funcionario criar(FuncionarioRequest request) {
        Funcionario funcionario = new Funcionario();
        preencher(funcionario, request);
        funcionario.persist();
        return funcionario;
    }

    @Transactional
    public Funcionario atualizar(Long id, FuncionarioRequest request) {
        Funcionario funcionario = Funcionario.findById(id);
        if (funcionario == null) {
            return null;
        }
        preencher(funcionario, request);
        return funcionario;
    }

    @Transactional
    public boolean excluir(Long id) {
        Funcionario funcionario = Funcionario.findById(id);
        if (funcionario == null) {
            return false;
        }
        funcionario.delete();
        return true;
    }

    private void preencher(Funcionario funcionario, FuncionarioRequest request) {
        funcionario.nome = request.nome;
        funcionario.cpf = request.cpf;
        funcionario.email = request.email;
        funcionario.cargo = request.cargo;
        funcionario.departamento = request.departamento;
        funcionario.dataAdmissao = request.dataAdmissao;
        funcionario.telefone = request.telefone;
    }
}
