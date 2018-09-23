package br.com.crashsolutions.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.crashsolutions.Conexao.Factory;
import br.com.crashsolutions.SG.ProdutoSG;

public class ProdutoDAO {
	
	private String sql;
	private Connection con;
	private ProdutoSG retornoLista = new ProdutoSG();
	private PreparedStatement stmInserir, stmConsulta, stmAlterar, stmListaConsulta;
	private ResultSet listaConsulta, resConsulta;
	public String Mensagem = null;
	
	public void inserir(ProdutoSG sgproduto) {
		
		con = new Factory().conectaBanco();
		sql = "insert into PRODUTO (produto,imagem,descricao,modelo,genero,tamanho,cor,categoria,valor_custo,valor_venda,quantidade) values (?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			stmInserir = con.prepareStatement(sql);
			
			stmInserir.setString(1,sgproduto.getProduto());
			stmInserir.setString(2,sgproduto.getImagem());
			stmInserir.setString(3,sgproduto.getDescricao());
			stmInserir.setString(4,sgproduto.getModelo());
			stmInserir.setString(5,sgproduto.getGenero());
			stmInserir.setString(6,sgproduto.getTamanho());
			stmInserir.setString(7,sgproduto.getCor());
			stmInserir.setString(8,sgproduto.getCategoria());
			stmInserir.setDouble(9,sgproduto.getValor_custo());
			stmInserir.setDouble(10,sgproduto.getValor_venda());
			stmInserir.setInt(11,sgproduto.getQuantidade());
			
			stmInserir.execute();
			stmInserir.close();
			
			Mensagem = "Cadastrado com sucesso!";
			System.out.println(Mensagem);
			
		} catch (Exception ex) {
			Mensagem = "Erro no Cadastro!";
			System.out.println(Mensagem + ex);
		} 
	}
	
	public ProdutoSG consulta(String geral) {
		
		con = new Factory().conectaBanco();
		sql = "select * from PRODUTO where idproduto = ? or produto = ?";
		
		try {
			stmConsulta = con.prepareStatement(sql);
			stmConsulta.setString(1, geral);
			stmConsulta.setString(2, geral);
			ResultSet resConsulta = stmConsulta.executeQuery();
			
			while (resConsulta.next()) {
				
				retornoLista.setIdproduto(resConsulta.getInt("idproduto"));
				retornoLista.setProduto(resConsulta.getString("produto"));
				retornoLista.setImagem(resConsulta.getString("imagem"));
				retornoLista.setDescricao(resConsulta.getString("descricao"));
				retornoLista.setModelo(resConsulta.getString("modelo"));
				retornoLista.setGenero(resConsulta.getString("genero"));
				retornoLista.setTamanho(resConsulta.getString("tamanho"));
				retornoLista.setCor(resConsulta.getString("cor"));
				retornoLista.setCategoria(resConsulta.getString("categoria"));
				retornoLista.setValor_custo(resConsulta.getDouble("valor_custo"));
				retornoLista.setValor_venda(resConsulta.getDouble("valor_venda"));
				retornoLista.setQuantidade(resConsulta.getInt("quantidade"));
	
			}
			stmConsulta.close();
			con.close();
			
		} catch (Exception ex) {
			System.out.println("Ocorreu um erro ao consultar: "+ex);
		}
		return retornoLista; 
	}
	
	public void alterar (ProdutoSG sgproduto) {
		
		con = new Factory().conectaBanco();
		sql = "update PRODUTO set produto =?, imagem =?, descricao =?, modelo =?, genero =?, tamanho =?, cor =?, categoria =?, valor_custo =?, valor_venda =?, quantidade =? where idproduto =?";
		
		try {
			
			stmAlterar = con.prepareStatement(sql);
			
			stmAlterar.setString(1,sgproduto.getProduto());
			stmAlterar.setString(2,sgproduto.getImagem());
			stmAlterar.setString(3,sgproduto.getDescricao());
			stmAlterar.setString(4,sgproduto.getModelo());
			stmAlterar.setString(5,sgproduto.getGenero());
			stmAlterar.setString(6,sgproduto.getTamanho());
			stmAlterar.setString(7,sgproduto.getCor());
			stmAlterar.setString(8,sgproduto.getCategoria());
			stmAlterar.setDouble(9,sgproduto.getValor_custo());
			stmAlterar.setDouble(10,sgproduto.getValor_venda());
			stmAlterar.setInt(11,sgproduto.getQuantidade());
			stmAlterar.setInt(12,sgproduto.getIdproduto());
			
			stmAlterar.execute();
			con.close();
			
			Mensagem = "Alterado com sucesso";
			System.out.println(Mensagem);
			
		} catch (Exception ex) {
			Mensagem = "Erro na Alteração!";
			System.out.println(Mensagem + ex);
		} 
	}
	
	public ArrayList<ProdutoSG> buscaTodos() {
		
		con = new Factory().conectaBanco();
		sql = "select * from PRODUTO";
		
		ArrayList <ProdutoSG> listartodos = new ArrayList<>();
		
		try {
			
			stmListaConsulta = con.prepareStatement(sql); 
			listaConsulta = stmListaConsulta.executeQuery();
			
			while (listaConsulta.next()) {
				
				ProdutoSG retornoLista = new ProdutoSG();
				
				retornoLista.setIdproduto(listaConsulta.getInt("idproduto"));
				retornoLista.setProduto(listaConsulta.getString("produto"));
				retornoLista.setImagem(listaConsulta.getString("imagem"));
				retornoLista.setDescricao(listaConsulta.getString("descricao"));
				retornoLista.setModelo(listaConsulta.getString("modelo"));
				retornoLista.setGenero(listaConsulta.getString("genero"));
				retornoLista.setTamanho(listaConsulta.getString("tamanho"));
				retornoLista.setCor(listaConsulta.getString("cor"));
				retornoLista.setCategoria(listaConsulta.getString("categoria"));
				retornoLista.setValor_custo(listaConsulta.getDouble("valor_custo"));
				retornoLista.setValor_venda(listaConsulta.getDouble("valor_venda"));
				retornoLista.setQuantidade(listaConsulta.getInt("quantidade"));
				
				listartodos.add(retornoLista);
			}
			stmListaConsulta.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("Erro " + e);
			return null;
		}
		return listartodos; 
	}
    
    public ArrayList<ProdutoSG> navbar (String geral) {
		
		con = new Factory().conectaBanco();
		
		ArrayList<ProdutoSG> lista = new ArrayList<>();
		sql = "select * from PRODUTO where produto like ?";
		
		try {
			
			stmConsulta = con.prepareStatement(sql);
			stmConsulta.setString(1, "%"+ geral +"%");
			listaConsulta = stmConsulta.executeQuery();
			
			while (listaConsulta.next()) {
				
				ProdutoSG retornoLista = new ProdutoSG();
                
				retornoLista.setIdproduto(listaConsulta.getInt("idproduto"));
				retornoLista.setProduto(listaConsulta.getString("produto"));
				retornoLista.setImagem(listaConsulta.getString("imagem"));
				retornoLista.setDescricao(listaConsulta.getString("descricao"));
				retornoLista.setModelo(listaConsulta.getString("modelo"));
				retornoLista.setGenero(listaConsulta.getString("genero"));
				retornoLista.setTamanho(listaConsulta.getString("tamanho"));
				retornoLista.setCor(listaConsulta.getString("cor"));
				retornoLista.setCategoria(listaConsulta.getString("categoria"));
				retornoLista.setValor_custo(listaConsulta.getDouble("valor_custo"));
				retornoLista.setValor_venda(listaConsulta.getDouble("valor_venda"));
				retornoLista.setQuantidade(listaConsulta.getInt("quantidade"));
				lista.add(retornoLista);
				
				
			}
			stmConsulta.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("Nada Encontrado " + e);
			return null;
		}
		return lista;
	}
    
    public ArrayList<ProdutoSG> produtolink(String geral) {
		
		con = new Factory().conectaBanco();
		ArrayList<ProdutoSG> produtolink = new ArrayList<>();
		
		sql = "select * from PRODUTO where genero= ? or categoria= ?";
		
		try {
			stmConsulta = con.prepareStatement(sql);
			stmConsulta.setString(1, geral);
			stmConsulta.setString(2, geral);
			resConsulta = stmConsulta.executeQuery();
			
			while (resConsulta.next()) {
				
				ProdutoSG retornoLista = new ProdutoSG();
				
				retornoLista.setIdproduto(resConsulta.getInt("idproduto"));
				retornoLista.setProduto(resConsulta.getString("produto"));
				retornoLista.setImagem(resConsulta.getString("imagem"));
				retornoLista.setDescricao(resConsulta.getString("descricao"));
				retornoLista.setModelo(resConsulta.getString("modelo"));
				retornoLista.setGenero(resConsulta.getString("genero"));
				retornoLista.setTamanho(resConsulta.getString("tamanho"));
				retornoLista.setCor(resConsulta.getString("cor"));
				retornoLista.setCategoria(resConsulta.getString("categoria"));
				retornoLista.setValor_custo(resConsulta.getDouble("valor_custo"));
				retornoLista.setValor_venda(resConsulta.getDouble("valor_venda"));
				retornoLista.setQuantidade(resConsulta.getInt("quantidade"));
				
				// ADICIONA OS DADOS DA LISTA
				produtolink.add(retornoLista);
	
			}
			stmConsulta.close();
			con.close();
			
		} catch (Exception ex) {
			System.out.println("Ocorreu um erro ao consultar: "+ex);
		}
		return produtolink; 
	}
    
}
