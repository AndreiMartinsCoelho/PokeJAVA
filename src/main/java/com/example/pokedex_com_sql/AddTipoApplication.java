package com.example.pokedex_com_sql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class AddTipoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addTipo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 770);
        stage.setTitle("Adicionar Tipo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
