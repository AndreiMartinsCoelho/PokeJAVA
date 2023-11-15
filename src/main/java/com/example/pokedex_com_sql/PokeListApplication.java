package com.example.pokedex_com_sql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PokeListApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PokeListApplication.class.getResource("pokedex-list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Lista de Pokemons!");
        stage.setScene(scene);
        stage.show();
    }

    // Método para iniciar a aplicação de listar os pokemons
    public static void main(String[] args) {
        launch(args);
    }
}

