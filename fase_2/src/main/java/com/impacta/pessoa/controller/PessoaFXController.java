package com.impacta.pessoa.controller;

import com.impacta.pessoa.entity.Pessoa;
import com.impacta.pessoa.exception.PessoaException;
import com.impacta.pessoa.service.PessoaService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PessoaFXController implements Initializable {

    @FXML
    private TextField inputCpf;

    @FXML
    private TextField inputNome;


    @FXML
    private TableView<Pessoa> table;

    @FXML
    private TableColumn<Pessoa, String> columnId;

    @FXML
    private TableColumn<Pessoa, String> columnCpf;

    @FXML
    private TableColumn<Pessoa, String> columnNome;

    private long id;
    PessoaService pessoaService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pessoaService = new PessoaService();
        atualizarTabela();
        selecionarLinhaTabela();
    }

    @FXML
    void alterar(ActionEvent event) {
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        Pessoa pessoa = new Pessoa(id, nome, cpf);

        try {
            Pessoa pessoaEntity = pessoaService.atualizar(pessoa);

            showAlert(pessoaEntity, null, "dados atualizados");
            atualizarTabela();

        } catch (RuntimeException e) {
            showAlert(null, e, null);
        }
    }

    @FXML
    void excluir(ActionEvent event) {
        pessoaService.excluirPessoa(id);
        atualizarTabela();
        inputCpf.setText("");
        inputNome.setText("");
    }

    @FXML
    void salvar(ActionEvent event) {
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        Pessoa pessoaentity = null;
        try {
            pessoaentity = pessoaService.cadastrar(new Pessoa(nome, cpf));
            showAlert(pessoaentity, null, "cadastro realizado");
            atualizarTabela();
        } catch (PessoaException e) {
            showAlert(pessoaentity, e, null);
        } catch (RuntimeException e) {
            showAlert(pessoaentity, e, null);
        }
    }


    private void showAlert(Pessoa pessoa, Exception e, String message) {
        Alert alert;
        if (pessoa == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setContentText(message);
            alert.showAndWait();
        }

    }

    void atualizarTabela() {
        ObservableList<Pessoa> list = FXCollections.observableArrayList();
        List<Pessoa> listPessoas = pessoaService.buscarPessoa();
        list.addAll(listPessoas);
        table.setItems(list);

        listPessoas.forEach(System.out::println);

        columnId.setCellValueFactory(pessoa -> {
            String value = String.valueOf(pessoa.getValue().getId());
            return new SimpleStringProperty(value);
        });

        columnNome.setCellValueFactory(pessoa ->
                new SimpleStringProperty(pessoa.getValue().getNome()));

        columnCpf.setCellValueFactory(pessoa ->
                new SimpleStringProperty(pessoa.getValue().getCpf()));

    }

    void selecionarLinhaTabela() {
        table.setRowFactory(pessoaTableView -> {
            TableRow<Pessoa> linhaTabela = new TableRow<>();

            linhaTabela.setOnMouseClicked(mouseEvent -> {
                int indexTable = table.getSelectionModel().getSelectedIndex();

                long id = table.getItems().get(indexTable).getId();
                this.id = id;

                String nome = table.getItems().get(indexTable).getNome();
                this.inputNome.setText(nome);

                String cpf = table.getItems().get(indexTable).getCpf();
                this.inputCpf.setText(cpf);

            });
            return linhaTabela;
        });
    }
}
