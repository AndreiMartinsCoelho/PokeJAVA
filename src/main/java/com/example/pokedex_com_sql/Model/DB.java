package com.example.pokedex_com_sql.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Model para conexão com o banco de dados
public class DB {
    private Connection conexao;
    private boolean conectado;
    private String usuario;
    private String senha;
    private String banco;

    private String msgErro;

    public DB( String usuario, String senha, String banco ) {
        this.usuario = usuario;
        this.senha = senha;
        this.banco = banco;
        conectar();
    }

    public Connection getConexao() {
        return conexao;
    }

    public String getMsgErro() {
        return msgErro;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + banco,usuario, senha);
            conectado = true;
        } catch (ClassNotFoundException e) {
            conectado = false;
            msgErro = "Driver não encontrado";
        } catch (SQLException e) {
            conectado = false;
            msgErro = "Erro ao conectar: ";
        }
    }
}
