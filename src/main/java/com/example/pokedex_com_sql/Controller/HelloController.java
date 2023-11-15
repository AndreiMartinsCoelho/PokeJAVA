package com.example.pokedex_com_sql.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.text.Text;
import com.example.pokedex_com_sql.Model.PokemonModel; // Importando a classe PokemonModel

public class HelloController {

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
    private Label aviso;

    //Evento para adicionar um novo pokemon
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
}