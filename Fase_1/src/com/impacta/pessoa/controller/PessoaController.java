package com.impacta.pessoa.controller;

import com.impacta.pessoa.entity.Pessoa;
import com.impacta.pessoa.service.PessoaService;

import java.util.List;

public class PessoaController {

    public Pessoa cadastrar(Pessoa pessoa){
        System.out.println("cadastro da pessoa:" + pessoa);
        PessoaService pessoaService = new PessoaService();
        return pessoaService.cadastrar(pessoa);
    }

    public Pessoa buscarPessoa(long id){
        PessoaService pessoaService = new PessoaService();
        return pessoaService.buscarPessoa(id);
    }

    public Pessoa buscarPessoa(String cpf){
        PessoaService pessoaService = new PessoaService();
        return pessoaService.buscarPessoa(cpf);
    }

    public List<Pessoa> buscarPessoa(){
        PessoaService pessoaService = new PessoaService();
        return pessoaService.buscarPessoa();
    }

    public Long excluirPessoa(long id){
        PessoaService pessoaService = new PessoaService();
        return pessoaService.excluirPessoa(id);
    }
}
