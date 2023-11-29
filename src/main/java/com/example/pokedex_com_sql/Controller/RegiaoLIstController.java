package com.example.pokedex_com_sql.Controller;

import com.example.pokedex_com_sql.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.example.pokedex_com_sql.Model.RegiaoModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.example.pokedex_com_sql.Model.DB;
import javafx.stage.Stage;

public class RegiaoLIstController {

    private Connection DB = new DB("root", "", "pokedex").getConexao();

    @FXML
    private TableColumn<RegiaoModel, String> clCidade;

    @FXML
    private TableColumn<RegiaoModel, String> clEquipe;

    @FXML
    private TableColumn<RegiaoModel, String> clInsignias;

    @FXML
    private TableColumn<RegiaoModel, Integer> clID;

    @FXML
    private TableColumn<RegiaoModel, String> clNome;

    @FXML
    private TableColumn<RegiaoModel, String> clProfessor;

    @FXML
    private TableView<RegiaoModel> clRegiao;

    @FXML
    private AnchorPane painelPrefeito;

    ResultSet rs;

    @FXML
    void btnVoltar(ActionEvent event) {
        System.out.println("Entrou");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("homeTela.fxml"));
            Scene painel2 = new Scene(loader.load(), 1440, 770);

            Stage stage = (Stage) painelPrefeito.getScene().getWindow();
            stage.setTitle("Home Pokedex");
            stage.setScene(painel2);
            stage.show();

        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de adicionar pokemon");
            e.printStackTrace();
        }
    }

    ObservableList<RegiaoModel> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        clEquipe.setCellValueFactory(new PropertyValueFactory<>("equipeAntagonista"));
        clInsignias.setCellValueFactory(new PropertyValueFactory<>("Insignias"));
        clProfessor.setCellValueFactory(new PropertyValueFactory<>("Professor"));
        clCidade.setCellValueFactory(new PropertyValueFactory<>("Cidades"));
        clNome.setCellValueFactory(new PropertyValueFactory<>("nomeRegiao"));
        clID.setCellValueFactory(new PropertyValueFactory<>("idRegiao"));
        try {
            //Chamando o PokemonModel
            Statement stmt = DB.createStatement();
            rs = stmt.executeQuery("SELECT * FROM regioes");
            while (rs.next()) {
                RegiaoModel regiao = new RegiaoModel();
                regiao.setIdRegiao(rs.getInt("idRegiao"));
                regiao.setNomeRegiao(rs.getString("Nome"));
                regiao.setProfessor(rs.getString("Professor"));
                regiao.setEquipeAntagonista(rs.getString("Equipe_Antagonista"));
                regiao.setInsignias(rs.getString("Insignias"));
                regiao.setCidades(rs.getString("Cidades"));
                lista.add(regiao);
            }
            clRegiao.setItems(lista);
        } catch (SQLException e) {
            System.out.println("Erro ao listar os pokemons"+ e.getMessage());
        }

        clRegiao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Define o idRegiao na instância de RegiaoModel
                newValue.setIdRegiao(newValue.getIdRegiao());
            }
        });
    }

    @FXML
    private void handleExcluirButton(ActionEvent event) {
        RegiaoModel regiaoSelecionada = clRegiao.getSelectionModel().getSelectedItem();

        if (regiaoSelecionada != null) {
            // Chame uma função para confirmar a exclusão e, se confirmado, execute a exclusão
            if (confirmarExclusao()) {
                lista.remove(regiaoSelecionada);
                // Chame a função para excluir do banco de dados se necessário
                regiaoSelecionada.excluirRegiao();
            }
        } else {
            exibirAlerta("Nenhuma região selecionada", "Por favor, selecione uma região para excluir.");
        }
    }

    private boolean confirmarExclusao() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja excluir esta região?");

        // Botões de confirmação
        ButtonType buttonTypeSim = new ButtonType("Sim", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNao = new ButtonType("Não", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

        // Mostra o alerta e espera pela resposta do usuário
        Optional<ButtonType> result = alert.showAndWait();

        // Retorna true se o usuário confirmar, false caso contrário
        return result.isPresent() && result.get() == buttonTypeSim;
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }


}
