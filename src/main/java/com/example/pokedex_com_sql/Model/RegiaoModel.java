package com.example.pokedex_com_sql.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Importando a classe DB
import com.example.pokedex_com_sql.Model.DB;
public class RegiaoModel {
    private int idRegiao;
    private String nomeRegiao;
    private String Professor;
    private String equipeAntagonista;
    private String Cidades;
    private String Insignias;
    private String msgAviso;
    private Connection conexao;

    public int getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public String getCidades() {
        return Cidades;
    }

    public void setCidades(String cidades) {
        Cidades = cidades;
    }

    public String getInsignias() {
        return Insignias;
    }

    public void setInsignias(String insignias) {
        Insignias = insignias;
    }

    public void setDB(Connection DB) {
        this.DB = DB;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public String getProfessor() {
        return Professor;
    }

    public void setProfessor(String professor) {
        Professor = professor;
    }

    public String getEquipeAntagonista() {
        return equipeAntagonista;
    }

    public void setEquipeAntagonista(String equipeAntagonista) {
        this.equipeAntagonista = equipeAntagonista;
    }

    public String getMsgAviso() {
        return msgAviso;
    }

    public void setMsgAviso(String msgAviso) {
        this.msgAviso = msgAviso;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    Connection DB = new DB("root", "", "pokedex").getConexao();

    //Função para inserir uma nova região no banco de dados...
    public void inserirRegiao(){
        String sql = "INSERT INTO regioes (Nome, Professor, Equipe_antagonista, Cidades, Insignias) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = DB.prepareStatement(sql);
            stmt.setString(1, this.nomeRegiao);
            stmt.setString(2, this.Professor);
            stmt.setString(3, this.equipeAntagonista);
            stmt.setString(4, this.Cidades);
            stmt.setString(5, this.Insignias);
            stmt.execute();
            stmt.close();
            this.msgAviso = "Região adicionada com sucesso!";
        } catch (SQLException e) {
            this.msgAviso = "Erro ao adicionar região!";
            e.printStackTrace();
        }
    }

}
