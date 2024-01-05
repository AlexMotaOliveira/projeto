package com.impacta.pessoa.repository.impl;

import com.impacta.pessoa.entity.Pessoa;
import com.impacta.pessoa.repository.PessoaRepository;

import java.sql.*;
import java.util.List;

public class PessoaRepositoryImpl implements PessoaRepository {


    @Override
    public Pessoa save(Pessoa pessoa) {
        return null;
    }

    @Override
    public Pessoa findById(long id) {

        String query = "SELECT * FROM pessoa where id =" + id;

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Pessoa pessoa = new Pessoa();
            while (resultSet.next()) {
                long primaryKey = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");

                pessoa.setId(primaryKey);
                pessoa.setNome(nome);
                pessoa.setCpf(cpf);

            }
            return pessoa;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pessoa findByNome(String nome) {
        return null;
    }

    @Override
    public Pessoa findByCpf(String cpf) {
        return null;
    }

    @Override
    public List<Pessoa> findByAll() {
        return null;
    }

    @Override
    public Long deleteById(long id) {
        return null;
    }

    @Override
    public Long deleteByCpf(String id) {
        return null;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return null;
    }

    private Connection getConnection() throws SQLException {
        String senha = "123456";
        String usuario = "root";
        String url = "jdbc:mysql://localhost:3306/impacta?useTimezone=true&serverTimezone=UTC";

        return DriverManager.getConnection(url, usuario, senha);
    }
}
