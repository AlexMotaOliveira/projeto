package com.impacta.pessoa.service;

import com.impacta.pessoa.entity.Pessoa;
import com.impacta.pessoa.repository.impl.PessoaRepositoryImpl;

public class PessoaService {

    public Pessoa cadastrar(Pessoa pessoa) {
        if (pessoa == null || pessoa.getNome() == null || pessoa.getCpf() == null) {
            //todo criar um exceção como regra de negocio
            System.out.println("Nome/Cpf não pode estar nulo");
            return null;
        }

        if (pessoa.getCpf().isEmpty() && pessoa.getNome().isEmpty()) {
            System.out.println("cpf/nome não pode estar vazio");
            return null;
        }
        //todo implementar o cadastro no banco
        return pessoa;
    }


    public Pessoa buscarPessoa(long id) {
        PessoaRepositoryImpl pessoaRepository = new PessoaRepositoryImpl();
        return pessoaRepository.findById(id);
    }
}
