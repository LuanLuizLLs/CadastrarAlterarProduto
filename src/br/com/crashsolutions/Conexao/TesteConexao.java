package br.com.crashsolutions.Conexao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.crashsolutions.Conexao.Factory;

public class TesteConexao {
	
	private static Connection con;
	
	public static void main(String[] args) throws SQLException {
         con = new Factory().conectaBanco();
         System.out.println("Conexão aberta!");
         con.close();
     }
}
