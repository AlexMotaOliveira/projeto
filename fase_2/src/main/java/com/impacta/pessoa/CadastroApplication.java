package com.impacta.pessoa;

import com.impacta.pessoa.config.ConnectionDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CadastroApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Cadastro!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {



        new ConnectionDataBase(args[0], args[1]);
        launch();
    }
}