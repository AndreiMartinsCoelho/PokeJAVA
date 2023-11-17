package com.example.pokedex_com_sql.Controller;

import com.example.pokedex_com_sql.HelloApplication;
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
import com.example.pokedex_com_sql.Model.PokemonModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.example.pokedex_com_sql.Model.DB;
import javafx.stage.Stage;

public class ListPokeController {

    private Connection DB = new DB("root", "", "pokedex").getConexao();

    @FXML
    private TableView<PokemonModel> clTabela;

    @FXML
    private TableColumn<PokemonModel, String> clNome;

    @FXML
    private TableColumn<PokemonModel, Float> clNumero;

    @FXML
    private TableColumn<PokemonModel, Float> clAtaque;

    @FXML
    private TableColumn<PokemonModel, Float> clDefesa;

    @FXML
    private TableColumn<PokemonModel, Float> clVelo;

    @FXML
    private TableColumn<PokemonModel, Float> clPeso;

    @FXML
    private TableColumn<PokemonModel, Float> clHP;

    @FXML
    private TableColumn<PokemonModel, String> clNomeTipo;

    @FXML
    private AnchorPane painelPrefeito;

    ResultSet rs;
    ObservableList<PokemonModel> lista = FXCollections.observableArrayList();

    //Método para listar os pokemons
    @FXML
    public void initialize(){
        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        clAtaque.setCellValueFactory(new PropertyValueFactory<>("Ataque"));
        clDefesa.setCellValueFactory(new PropertyValueFactory<>("Defesa"));
        clPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        clHP.setCellValueFactory(new PropertyValueFactory<>("HP"));
        clVelo.setCellValueFactory(new PropertyValueFactory<>("Velocidade"));
        clNomeTipo.setCellValueFactory(new PropertyValueFactory<>("nomeTipo"));
        try {
            //Chamando o PokemonModel
            Statement stmt = DB.createStatement();
            rs = stmt.executeQuery(
                    "SELECT p.*, t.Nome AS NomeTipo " +
                    "FROM pokedex.pokemon p " +
                    "JOIN pokedex.tipo t ON p.tipo_id_tipo = t.idTipo " +
                    "ORDER BY p.numero");
            while (rs.next()) {
                PokemonModel pokemon = new PokemonModel();
                pokemon.setNome(rs.getString("nome"));
                pokemon.setNumero(rs.getFloat("Numero"));
                pokemon.setHP(rs.getFloat("HP"));
                pokemon.setAtaque(rs.getFloat("Ataque"));
                pokemon.setVelocidade(rs.getFloat("Velocidade"));
                pokemon.setDefesa(rs.getFloat("Defesa"));
                pokemon.setVelocidade(rs.getFloat("Velocidade"));
                pokemon.setPeso(rs.getFloat("Peso"));
                pokemon.setNomeTipo(rs.getString("nomeTipo"));
                lista.add(pokemon);
            }
            clTabela.setItems(lista);
        } catch (SQLException e) {
            System.out.println("Erro ao listar os pokemons"+ e.getMessage());
        }
    }

    @FXML
    void onVoltar(ActionEvent event) {
        System.out.println("Entrou");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("hello-view.fxml"));
            Scene painel2 = new Scene(loader.load(), 1440, 770);

            Stage stage = (Stage) painelPrefeito.getScene().getWindow();
            stage.setTitle("Adicionar Pokemon!");
            stage.setScene(painel2);
            stage.show();

        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de adicionar pokemon");
            e.printStackTrace();
        }
    }

}

