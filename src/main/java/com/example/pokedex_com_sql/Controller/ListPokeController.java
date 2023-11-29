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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.example.pokedex_com_sql.Model.PokemonModel;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

import com.example.pokedex_com_sql.Model.DB;
import javafx.stage.Stage;

public class ListPokeController {

    private Connection DB = new DB("root", "", "pokedex").getConexao();

    @FXML
    private TableView<PokemonModel> clTabela;

    @FXML
    private TableColumn<PokemonModel, String> clNome;

    @FXML
    private TableColumn<PokemonModel, Integer> clID;

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
        clID.setCellValueFactory(new PropertyValueFactory<>("idPokemon"));
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
                pokemon.setIdPokemon(rs.getInt("idPokemon"));
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

    @FXML
    void handleExcluirButton(ActionEvent event) {
        PokemonModel pokemon = clTabela.getSelectionModel().getSelectedItem();
        if (pokemon != null) {
            // Substitua o trecho no método handleExcluirButton por este código
            String sql = "DELETE FROM pokemon WHERE idPokemon = ?";
            try {
                if (!confirmarExclusao()) return;
                PreparedStatement ps = DB.prepareStatement(sql);
                ps.setInt(1, pokemon.getIdPokemon());
                ps.executeUpdate();
                ps.close();
                lista.remove(pokemon);
                exibirAlerta("Sucesso", "Pokemon excluído com sucesso!");
            } catch (SQLException e) {
                exibirAlerta("Erro", "Erro ao excluir pokemon!");
                e.printStackTrace();
            }

        } else {
            exibirAlerta("Erro", "Selecione um pokemon para excluir!");
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

