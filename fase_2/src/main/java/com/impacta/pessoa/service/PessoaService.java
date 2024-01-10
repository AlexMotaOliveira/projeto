package com.impacta.pessoa.service;

import com.impacta.pessoa.entity.Pessoa;
import com.impacta.pessoa.exception.PessoaException;
import com.impacta.pessoa.repository.impl.PessoaRepositoryImpl;

import java.util.List;

public class PessoaService {

    private final PessoaRepositoryImpl pessoaRepository;

    public PessoaService() {
        this.pessoaRepository = new PessoaRepositoryImpl();
    }

    public Pessoa cadastrar(Pessoa pessoa) {
        validarDadosDeCadastrar(pessoa);
        validarCpfExistente(pessoa.getCpf());
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPessoa(long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa buscarPessoa(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    public List<Pessoa> buscarPessoa() {
        return pessoaRepository.findByAll();
    }

    public Long excluirPessoa(long id) {
        return pessoaRepository.deleteById(id);
    }

    public Pessoa atualizar(Pessoa pessoa) {
        validarDadosDeCadastrar(pessoa);
        validarCpfExistente(pessoa.getCpf());
        return pessoaRepository.update(pessoa);
    }

    private static void validarDadosDeCadastrar(Pessoa pessoa) {
        if (pessoa == null || pessoa.getNome() == null || pessoa.getCpf() == null) {
            throw new PessoaException("Nome/Cpf não pode estar nulo");
        }

        if (pessoa.getCpf().isBlank() || pessoa.getNome().isBlank()) {
            throw new PessoaException("cpf/nome não pode estar vazio");
        }

        if (pessoa.getCpf().length() != 11) {
            throw new PessoaException("cpf não tem 11 digitos");
        }
    }

    private void validarCpfExistente(String cpf) {
        if (buscarPessoa(cpf) == null) {
        } else {
            throw new PessoaException("já existem uma pessoa com o mesmo cpf");
        }
    }


}
