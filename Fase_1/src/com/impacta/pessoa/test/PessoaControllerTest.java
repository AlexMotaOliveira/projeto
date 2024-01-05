package com.impacta.pessoa.test;

import com.impacta.pessoa.controller.PessoaController;
import com.impacta.pessoa.entity.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PessoaControllerTest {

    @Test
    void cadastraPessoaTest() {
        PessoaController pessoaController = new PessoaController();
//        Assertions.assertNotNull(pessoaController);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Alex Mota");
        pessoa.setCpf("12345678912");

        Pessoa pessoaEntity = pessoaController.cadastrar(pessoa);

        Assertions.assertNotNull(pessoaEntity);
    }

    @Test
    void cadastraPessoaNomeOuCpfVazioTest(){
        PessoaController pessoaController = new PessoaController();

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("");
        pessoa.setCpf("");

        Pessoa pessoaEntity = pessoaController.cadastrar(null);
        Assertions.assertNull(pessoaEntity);
    }

    @Test
    void buscarPessoa(){
        PessoaController pessoaController = new PessoaController();
        Pessoa pessoaEntity = pessoaController.buscarPessoa(1);
        Assertions.assertNotNull(pessoaEntity);
        Assertions.assertEquals(1, pessoaEntity.getId());
        System.out.println(pessoaEntity);

    }
}
