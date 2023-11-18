package com.example.pokedex_com_sql.Controller;

import com.example.pokedex_com_sql.HelloApplication;
import com.example.pokedex_com_sql.Model.TipoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.example.pokedex_com_sql.Model.RegiaoModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try {
            //Chamando o PokemonModel
            Statement stmt = DB.createStatement();
            rs = stmt.executeQuery("SELECT * FROM regioes");
            while (rs.next()) {
                RegiaoModel regiao = new RegiaoModel();
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
    }


}
