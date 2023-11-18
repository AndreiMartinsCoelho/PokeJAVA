package com.example.pokedex_com_sql.Controller;

import com.example.pokedex_com_sql.HelloApplication;
import com.example.pokedex_com_sql.Model.PokemonModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import com.example.pokedex_com_sql.Model.TipoModel; // Importando a classe PokemonModel

public class AddTipoController {
    @FXML
    private TextField onForte;
    @FXML
    private TextField onFraco;
    @FXML
    private TextField onTipo;
    @FXML
    private AnchorPane painelPrefeito;

    //Evento para adicionar um novo pokemon...
    @FXML
    void addTipo(ActionEvent event) {
        TipoModel tipo = new TipoModel();
        tipo.setNomeTipo(onTipo.getText());
        tipo.setFracoContra(onFraco.getText());
        tipo.setForteContra(onForte.getText());
        tipo.inserirTipo();

        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Alerta");
        aviso.setHeaderText("Houve alguma atualização...");
        aviso.setContentText(tipo.getMsgAviso());
        aviso.showAndWait();
    }

    //Evento para voltar para a tela inicial...
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
            System.out.println("Erro ao carregar a tela de adicionar tipo");
            e.printStackTrace();
        }
    }
}