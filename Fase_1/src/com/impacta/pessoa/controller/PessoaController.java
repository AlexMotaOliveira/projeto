package com.impacta.pessoa.controller;

import com.impacta.pessoa.entity.Pessoa;
import com.impacta.pessoa.service.PessoaService;

public class PessoaController {

    public Pessoa cadastrar(Pessoa pessoa){
        System.out.println("cadastro da pessoa:" + pessoa);
        PessoaService pessoaService = new PessoaService();
        pessoaService.cadastrar(pessoa);
        return pessoa;
    }

    public Pessoa buscarPessoa(long id){
        PessoaService pessoaService = new PessoaService();
        return pessoaService.buscarPessoa(id);
    }
}
