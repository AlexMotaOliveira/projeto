package com.impacta.pessoa.service;

import com.impacta.pessoa.entity.Pessoa;

public class PessoaService {

    public Pessoa cadastrar(Pessoa pessoa){
        if(pessoa == null || pessoa.getNome() == null || pessoa.getCpf() == null){
            //todo criar um exceção como regra de negocio
            System.out.println("Nome/Cpf não pode estar nulo");
            return null;
        }

        if (pessoa.getCpf().isEmpty() && pessoa.getNome().isEmpty()){
            System.out.println("cpf/nome não pode estar vazio");
            return null;
        }
        //todo implementar o cadastro no banco
        return pessoa;
    }


    public Pessoa buscarPessoa(long id) {

        //todo add busca pessoa pelo id no banco
        if(id == 1) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(2);
            pessoa.setNome("Alex Mota");
            pessoa.setCpf("12345678932");
            return pessoa;
        }

        System.out.println("não localizei a pessoa com o id 1");
        return null;
    }
}
