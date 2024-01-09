package com.impacta.pessoa.controller;

import com.impacta.pessoa.entity.Pessoa;
import com.impacta.pessoa.exception.PessoaException;
import com.impacta.pessoa.service.PessoaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PessoaFXController implements Initializable {

    @FXML
    private TextField inputCpf;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField id;

    @FXML
    private TableView<?> pessoas;

    PessoaService pessoaService;

    @FXML
    void alterar(ActionEvent event) {
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        long idLong = Long.parseLong(id.getText());

        Pessoa pessoa = new Pessoa(idLong, nome, cpf);

        pessoaService.atualizar(pessoa);


    }

    @FXML
    void excluir(ActionEvent event) {
        long idLong = Long.parseLong(id.getText());
        pessoaService.excluirPessoa(idLong);

    }

    @FXML
    void salvar(ActionEvent event) {
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        Pessoa pessoaentity = null;
        try {

            pessoaentity = pessoaService.cadastrar(new Pessoa(nome, cpf));
            String castId = String.valueOf(pessoaentity.getId());

            id.setText(castId);

        } catch (PessoaException e) {
            showAlert(pessoaentity, e);
        } catch (RuntimeException e) {
            showAlert(pessoaentity, e);
        }
        showAlert(pessoaentity, null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pessoaService = new PessoaService();
    }

    void showAlert(Pessoa pessoa, Exception e) {
        Alert alert;
        if (pessoa == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setContentText("Pessoa cadastrada com sucesso");
            alert.showAndWait();
        }

    }
}
