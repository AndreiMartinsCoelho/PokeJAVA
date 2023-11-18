package com.example.pokedex_com_sql.Controller;

import com.example.pokedex_com_sql.HelloApplication;
import com.example.pokedex_com_sql.Model.PokemonModel;
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
import com.example.pokedex_com_sql.Model.TipoModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.example.pokedex_com_sql.Model.DB;
import javafx.stage.Stage;
public class TipoListController {

    private Connection DB = new DB("root", "", "pokedex").getConexao();

    @FXML
    private TableColumn<TipoModel, String> clForte;

    @FXML
    private TableColumn<TipoModel, String> clFraqueza;

    @FXML
    private TableColumn<TipoModel, String> clTipo;

    @FXML
    private TableView<TipoModel> clTipos;

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

    ObservableList<TipoModel> lista = FXCollections.observableArrayList();

    //Evento para listar os pokemons...
    @FXML
    public void initialize(){
        clTipo.setCellValueFactory(new PropertyValueFactory<>("nomeTipo"));
        clFraqueza.setCellValueFactory(new PropertyValueFactory<>("fracoContra"));
        clForte.setCellValueFactory(new PropertyValueFactory<>("forteContra"));
        try {
            //Chamando o PokemonModel
            Statement stmt = DB.createStatement();
            rs = stmt.executeQuery("SELECT * FROM tipo");
            while (rs.next()) {
                TipoModel tipo = new TipoModel();
                tipo.setNomeTipo(rs.getString("Nome"));
                tipo.setForteContra(rs.getString("Forte_Contra"));
                tipo.setFracoContra(rs.getString("Fraco_Contra"));
                lista.add(tipo);
            }
            clTipos.setItems(lista);
        } catch (SQLException e) {
            System.out.println("Erro ao listar os pokemons"+ e.getMessage());
        }
    }

}
