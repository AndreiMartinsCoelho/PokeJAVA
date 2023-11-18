package com.example.pokedex_com_sql.Controller;

import com.example.pokedex_com_sql.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.pokedex_com_sql.Model.PokemonModel; // Importando a classe PokemonModel
import java.io.IOException;

public class HelloController {

    @FXML
    private VBox painelPrefeito;

    @FXML
    private TextField ataqPO;

    @FXML
    private TextField defPO;

    @FXML
    private TextField tipoPO;

    @FXML
    private TextField hpPO;

    @FXML
    private TextField nomePO;

    @FXML
    private TextField numPO;

    @FXML
    private TextField pesoPO;

    @FXML
    private TextField velPO;

    @FXML
    void addPO(ActionEvent event) {
        PokemonModel pokemon = new PokemonModel();
        pokemon.setNome(nomePO.getText());
        pokemon.setHP(Float.parseFloat(hpPO.getText()));
        pokemon.setNumero(Float.parseFloat(numPO.getText()));
        pokemon.setAtaque(Float.parseFloat(ataqPO.getText()));
        pokemon.setDefesa(Float.parseFloat(defPO.getText()));
        pokemon.setVelocidade(Float.parseFloat(velPO.getText()));
        pokemon.setPeso(Float.parseFloat(pesoPO.getText()));
        pokemon.setNomeTipo(tipoPO.getText());
        pokemon.inserir();

        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Alerta");
        aviso.setHeaderText("Houve alguma atualização...");
        aviso.setContentText(pokemon.getMsgAviso());
        aviso.showAndWait();
    }

    @FXML
    void btnVoltar(ActionEvent event) {
        System.out.println("Entrou");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("homeTela.fxml"));
            Scene painel2 = new Scene(loader.load(), 1440, 770);

            Stage stage = (Stage) painelPrefeito.getScene().getWindow();
            stage.setTitle("Pokedex");
            stage.setScene(painel2);
            stage.show();

        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de adicionar pokemon");
            e.printStackTrace();
        }
    }

}