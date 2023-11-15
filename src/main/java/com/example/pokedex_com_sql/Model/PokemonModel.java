package com.example.pokedex_com_sql.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
//Importando a classe DB
import com.example.pokedex_com_sql.Model.DB;

public class PokemonModel {
    private int idPokemon;

    private String imgPokemon;
    private String nome;

    private String nomeTipo;
    private float HP;
    private float Numero;
    private float Ataque;
    private float Defesa;

    private float Velocidade;

    private float Peso;

    private Connection conexao;

    private String msgAviso;

    //Getters e Setters
    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public String getImgPokemon() {
        return imgPokemon;
    }

    public void setImgPokemon(String imgPokemon) {
        this.imgPokemon = imgPokemon;
    }

    public String getMsgAviso() {
        return msgAviso;
    }

    public void setMsgAviso(String msgAviso) {
        this.msgAviso = msgAviso;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public float getNumero() {
        return Numero;
    }

    public void setNumero(float numero) {
        Numero = numero;
    }

    public float getAtaque() {
        return Ataque;
    }

    public void setAtaque(float ataque) {
        Ataque = ataque;
    }

    public float getDefesa() {
        return Defesa;
    }

    public void setDefesa(float defesa) {
        Defesa = defesa;
    }

    public float getVelocidade() {
        return Velocidade;
    }

    public void setVelocidade(float velocidade) {
        Velocidade = velocidade;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public float getPeso() {
        return Peso;
    }

    public void setPeso(float peso) {
        Peso = peso;
    }

    Connection DB = new DB("root", "", "pokedex").getConexao();

    //Adicionar o tipo na tabela Pokemon pelo nome
    public void buscarTipo() {
        String sql = "SELECT idTipo FROM tipo WHERE nome = ?";
        try {
            PreparedStatement ps = DB.prepareStatement(sql);
            ps.setString(1, this.nomeTipo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idTipo = rs.getInt("idTipo");
            ps.close();
            this.nomeTipo = String.valueOf(idTipo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Salvar o pokemon na tabela Pokemon
    public void inserir () {
        //Chamando o método buscarTipo
        buscarTipo();

        //Inserindo o pokemon na tabela Pokemon
        String sql = "INSERT INTO pokemon (nome, HP, Numero, Ataque, Defesa, Velocidade, Peso, tipo_id_tipo, imgPokemon) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DB.prepareStatement(sql);
            ps.setString(1, this.nome);
            ps.setFloat(2, this.HP);
            ps.setFloat(3, this.Numero);
            ps.setFloat(4, this.Ataque);
            ps.setFloat(5, this.Defesa);
            ps.setFloat(6, this.Velocidade);
            ps.setFloat(7, this.Peso);
            ps.setString(8, this.nomeTipo);
            ps.setString(9, this.imgPokemon);
            ps.execute();
            ps.close();
            this.msgAviso = "Pokemon adicionado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            this.msgAviso = "Erro ao adicionar pokemon!";
        }
    }

}
