package com.impacta.pessoa.repository.impl;

import com.impacta.pessoa.entity.Pessoa;
import com.impacta.pessoa.repository.PessoaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepositoryImpl implements PessoaRepository {


    @Override
    public Pessoa save(Pessoa pessoa) {


        String query = "INSERT INTO pessoa (nome, cpf) VALUES (?, ?);";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {

            if (findByCpf(pessoa.getCpf()) == null) {
                prepareStatement.setString(1, pessoa.getNome());
                prepareStatement.setString(2, pessoa.getCpf());

                long result = prepareStatement.executeUpdate();
                if (result > 0) {
                    return findByCpf(pessoa.getCpf());
                }
            } else {
                System.out.println("já existem uma pessoa com o mesmo cpf");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pessoa findById(long id) {

        String query = "SELECT * FROM pessoa where id =" + id;

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Pessoa pessoa = getPessoa(resultSet);
            connection.close();
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

        String query = "SELECT * FROM pessoa where cpf =" + cpf;

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return getPessoa(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pessoa> findByAll() {

        String query = "SELECT * FROM pessoa";

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Pessoa> pessoaList = new ArrayList<>();

            Pessoa pessoa;
            do {
                pessoa = getPessoa(resultSet);
                if (pessoa != null) {
                    pessoaList.add(pessoa);
                }
            } while (pessoa != null);
            return pessoaList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long deleteById(long id) {

        String query = "DELETE FROM pessoa WHERE id =" + id;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            long result = statement.executeUpdate(query);
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Long deleteByCpf(String id) {
        return null;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        //todo validar se o cpf já existe no banco

        String query = "UPDATE pessoa SET nome = ?, cpf = ? WHERE id = ?;";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {

            prepareStatement.setString(1, pessoa.getNome());
            prepareStatement.setString(2, pessoa.getCpf());
            prepareStatement.setLong(3, pessoa.getId());

            long result = prepareStatement.executeUpdate();
            if (result > 0) {
                return findById(pessoa.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Connection getConnection() throws SQLException {
        String senha = "123456";
        String usuario = "root";
        String url = "jdbc:mysql://localhost:3306/impacta?useTimezone=true&serverTimezone=UTC";

        return DriverManager.getConnection(url, usuario, senha);
    }

    private Pessoa getPessoa(ResultSet resultSet) throws SQLException {
        Pessoa pessoa = new Pessoa();
        if (resultSet.next()) {
            long id = resultSet.getLong("id");
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");

            pessoa.setId(id);
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            return pessoa;
        }
        return null;
    }
}
