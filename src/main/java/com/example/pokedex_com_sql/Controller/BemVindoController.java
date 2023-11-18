package com.example.pokedex_com_sql.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BemVindoController {
    @FXML
    private AnchorPane painelPrefeito;

    @FXML
    void onEntrar(ActionEvent event) {
        System.out.println("Entrou");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/pokedex_com_sql/homeTela.fxml"));
            AnchorPane painel = loader.load();

            painelPrefeito.getChildren().setAll(painel);

        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de adicionar pokemon");
            e.printStackTrace();
        }
    }
}



