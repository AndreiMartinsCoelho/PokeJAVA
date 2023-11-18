package com.example.pokedex_com_sql.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Importando a classe DB
import com.example.pokedex_com_sql.Model.DB;
public class TipoModel {
    private int idTipo;
    private String nomeTipo;
    private String forteContra;
    private String fracoContra;
    private Connection conexao;
    private String msgAviso;

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public String getMsgAviso() {
        return msgAviso;
    }

    public void setMsgAviso(String msgAviso) {
        this.msgAviso = msgAviso;
    }

    public Connection getDB() {
        return DB;
    }

    public void setDB(Connection DB) {
        this.DB = DB;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public String getForteContra() {
        return forteContra;
    }

    public void setForteContra(String forteContra) {
        this.forteContra = forteContra;
    }

    public String getFracoContra() {
        return fracoContra;
    }

    public void setFracoContra(String fracoContra) {
        this.fracoContra = fracoContra;
    }

    Connection DB = new DB("root", "", "pokedex").getConexao();

    //Método para inserir um novo tipo
    public void inserirTipo(){
        String SQL = "INSERT INTO tipo (Nome, Forte_Contra, Fraco_Contra) VALUES (?, ?, ?)";
        try{
            PreparedStatement stmt = DB.prepareStatement(SQL);
            stmt.setString(1, this.nomeTipo);
            stmt.setString(2, this.forteContra);
            stmt.setString(3, this.fracoContra);
            stmt.execute();
            stmt.close();
            this.msgAviso = "Tipo inserido com sucesso!";
        }catch (SQLException e){
            e.printStackTrace();
            this.msgAviso = "Erro ao inserir tipo!";
        }
    }
}
