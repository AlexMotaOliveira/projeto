package com.impacta.pessoa.repository;

import com.impacta.pessoa.entity.Pessoa;

import java.util.List;

public interface PessoaRepository {

    Pessoa save (Pessoa pessoa);

    Pessoa findById(long id);

    Pessoa findByNome(String nome);

    Pessoa findByCpf(String cpf);

    List<Pessoa> findByAll();

    Long deleteById(long id);
    Long deleteByCpf(String id);

    Pessoa update(Pessoa pessoa);

}
