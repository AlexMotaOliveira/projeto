package com.impacta.pessoa.test;

import com.impacta.pessoa.controller.PessoaController;
import com.impacta.pessoa.entity.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class PessoaControllerTest {

    @Test
    void cadastraPessoaTest() {
        PessoaController pessoaController = new PessoaController();
//        Assertions.assertNotNull(pessoaController);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Jose Silva");
        pessoa.setCpf("8541254789");

        Pessoa pessoaEntity = pessoaController.cadastrar(pessoa);

        Assertions.assertNotNull(pessoaEntity);
        System.out.println(pessoaEntity);
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
    void buscarPessoaPorId(){
        PessoaController pessoaController = new PessoaController();
        Pessoa pessoaEntity = pessoaController.buscarPessoa(1);
        Assertions.assertNotNull(pessoaEntity);
        Assertions.assertEquals(1, pessoaEntity.getId());
        System.out.println(pessoaEntity);

    }

    @Test
    void buscarPessoaPorCpf(){
        String cpf = "12345678936";
        PessoaController pessoaController = new PessoaController();
        Pessoa pessoaEntity = pessoaController.buscarPessoa(cpf);
        Assertions.assertNotNull(pessoaEntity);
        Assertions.assertEquals(cpf, pessoaEntity.getCpf());
        System.out.println(pessoaEntity);

    }

    @Test
    void buscarTodos(){
        PessoaController pessoaController = new PessoaController();
        List<Pessoa> pessoaList = pessoaController.buscarPessoa();
        Assertions.assertNotNull(pessoaList);
        Assertions.assertFalse(pessoaList.isEmpty());

        pessoaList.forEach(pessoa -> System.out.println());
        pessoaList.forEach(Pessoa::imprimir);

    }

    @Test
    void excluirPessoa() {
        PessoaController pessoaController = new PessoaController();
        long result = pessoaController.excluirPessoa(1);
        Assertions.assertEquals(1, result);

    }

}
