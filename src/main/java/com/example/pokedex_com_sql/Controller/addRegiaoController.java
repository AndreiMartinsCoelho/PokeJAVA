package com.example.pokedex_com_sql.Controller;

import com.example.pokedex_com_sql.HelloApplication;
import com.example.pokedex_com_sql.Model.TipoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import com.example.pokedex_com_sql.Model.RegiaoModel; // Importando a classe PokemonModel
public class addRegiaoController {

    @FXML
    private TextField OnEquipe;

    @FXML
    private TextField onNome;

    @FXML
    private TextField onProf;

    @FXML
    private AnchorPane painelPrefeito;

    @FXML
    private TextField onCidades;

    @FXML
    private TextField onInsignias;

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

    @FXML
    void onAddRegiao(ActionEvent event) {
        RegiaoModel regiao = new RegiaoModel();
        regiao.setNomeRegiao(onNome.getText());
        regiao.setProfessor(onProf.getText());
        regiao.setEquipeAntagonista(OnEquipe.getText());
        regiao.setCidades(onCidades.getText());
        regiao.setInsignias(onInsignias.getText());
        regiao.inserirRegiao();

        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Alerta");
        aviso.setHeaderText("Houve alguma atualização...");
        aviso.setContentText(regiao.getMsgAviso());
        aviso.showAndWait();
    }

}
