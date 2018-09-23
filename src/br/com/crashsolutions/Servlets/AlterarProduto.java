package br.com.crashsolutions.Servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.crashsolutions.DAO.ProdutoDAO;
import br.com.crashsolutions.SG.ProdutoSG;

@WebServlet("/AlterarProduto")
public class AlterarProduto extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public AlterarProduto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// INTANCIA O SG E DAO
		ProdutoDAO dao = new ProdutoDAO();
		ProdutoSG sg = new ProdutoSG();
		
		// BUSCA A AÇÃO NO DAO QUE BUSCA OS DADOS DO PRODUTO
		ArrayList<ProdutoSG> lista = dao.buscaTodos();  
		request.setAttribute("lista_produto", lista);
		
		// CONSULTA PELO ID DO PRODUTO
		sg = dao.consulta(request.getParameter("idproduto"));
		
		if (request.getParameter("idproduto") != null) {
			
			try {
				
				// BUSCA OS DADOS NO BANCO COM O SG
				Integer Idproduto = sg.getIdproduto();
				String Produto = sg.getProduto();
				String Imagem = sg.getImagem();
				String Descricao = sg.getDescricao();
				String Modelo = sg.getModelo();
				String Genero = sg.getGenero();
				String Tamanho = sg.getTamanho();
				String Cor = sg.getCor();
				String Categoria = sg.getCategoria();
				Double ValorCusto = sg.getValor_custo();
				Double ValorVenda = sg.getValor_venda();
				Integer Quantidade = sg.getQuantidade();
				
				// INSERE OS DADOS NOS CAMPOS DA PÁGINA JSP
				request.setAttribute("idproduto", Idproduto);
				request.setAttribute("produto", Produto);
				request.setAttribute("imagem", Imagem);
				request.setAttribute("descricao", Descricao);
				request.setAttribute("modelo", Modelo);
				request.setAttribute("genero", Genero);
				request.setAttribute("tamanho", Tamanho);
				request.setAttribute("cor", Cor);
				request.setAttribute("categoria", Categoria);
				request.setAttribute("valor_custo", ValorCusto);
				request.setAttribute("valor_venda", ValorVenda);
				request.setAttribute("quantidade", Quantidade);		
				
				// VERIFICAÇÃO DO CAMPO GENERO PARA INSERIR NO SELECT
				if (Genero.trim().equals(Genero) == Genero.trim().equals("Masculino") ) {
					
					request.setAttribute("genero", Genero);
					request.setAttribute("genero2", "Feminino");
				} else {
					
					request.setAttribute("genero", Genero);
					request.setAttribute("genero2", "Masculino");
				}
				
				// VERIFICAÇÃO DO CAMPO CATEGORIA PARA INSERIR NO SELECT
				if (Categoria.trim().equals(Categoria) == Categoria.trim().equals("Series")) {
					
					request.setAttribute("categoria", Categoria);
					request.setAttribute("categoria2", "Games");
					request.setAttribute("categoria3", "Animes");
					request.setAttribute("categoria4", "Filmes");
				} else {
					
					if (Categoria.trim().equals(Categoria) == Categoria.trim().equals("Games")) {
						
						request.setAttribute("categoria", Categoria);
						request.setAttribute("categoria2", "Animes");
						request.setAttribute("categoria3", "Filmes");
						request.setAttribute("categoria4", "Series");
					} else {
						if (Categoria.trim().equals(Categoria) == Categoria.trim().equals("Animes")) {
							
							request.setAttribute("categoria", Categoria);
							request.setAttribute("categoria2", "Filmes");
							request.setAttribute("categoria3", "Series");
							request.setAttribute("categoria4", "Games");
						} else {
							
							request.setAttribute("categoria", Categoria);
							request.setAttribute("categoria2", "Series");
							request.setAttribute("categoria3", "Games");
							request.setAttribute("categoria4", "Animes");
						}
					}
				}
			
			} catch (Exception ex) {
				System.out.println("Erro ao consultar os dados:"+ ex);
			}
		}
		
		// EXIBIR TELA APÓS CADASTRAR O PRODUTO
		request.getRequestDispatcher("AlterarProduto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// INSTANCIO AS CLASSES DAO E SG
		ProdutoDAO produtodao = new ProdutoDAO();
		ProdutoSG produtosg = new ProdutoSG();
		
		String caminho = null, Imagem = null;
		
		// UPLOAD DA IMAGEM
		if (ServletFileUpload.isMultipartContent(request)) {
	        try {
	            // FAZ O PARSE DO REQUEST
	            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	            
	            for (FileItem item : multiparts) {
	            	
	           		// PROCESSO DE ARQUIVOS  
	                if (!item.isFormField()) {
	                	
	                	// BUSCA O CAMINHO DO ARQUIVO 
	                	caminho  = item.getName().toString();
	                 
	                	// CADASTRA APENAS QUANDO HÁ UM ARQUIVO FOI SELECIONADO
	                    if (caminho != "") {
		                	
		                    // BUSCA O NOME DO ARQUIVO 
			                Imagem = caminho.substring(caminho.lastIndexOf("\\")+1);
		                    	
		                	// ENVIA O ARQUIVO PARA A PASTA DESEJADA
		                	item.write(new File(("C:\\Users\\luanl\\Desktop\\Eclipse\\T-Shirt Games Web\\WebContent\\resources\\img\\img-produtos") + File.separator + Imagem));		                	
	                    } 
	                }
	                
	                // PROCESSO DE DADOS
	                if (item.isFormField()) {
	                	
	                	// CADASTRO DE DADOS NO BANCO
	                	if (item.getFieldName().equals("idproduto")) {
	                		
	                		produtosg.setIdproduto(Integer.parseInt(item.getString())); 
	                	}
	                	if (item.getFieldName().equals("produto")) {
	                		
	                		produtosg.setProduto(item.getString()); 
	                	}
	                	if (caminho == "") {
	                		if (item.getFieldName().equals("imagem")) {
		                		
		                		produtosg.setImagem(item.getString());
		                	} 
	                	} else {
	                		// CADASTRA APENAS SE A IMAGEM É ALTERADO
		                	produtosg.setImagem(Imagem);
	                	}
	                	if (item.getFieldName().equals("descricao")) {
	                		
	                		produtosg.setDescricao(item.getString());
	                	}
	                	if (item.getFieldName().equals("modelo")) {
	                		
	                		produtosg.setModelo(item.getString());
	                	}
	                	if (item.getFieldName().equals("genero")) {
	                		
	                		produtosg.setGenero(item.getString());
	                	}
	                	if (item.getFieldName().equals("tamanho")) {
	                		
	                		produtosg.setTamanho(item.getString());
	                	}
	                	if (item.getFieldName().equals("cor")) {
	                		
	                		produtosg.setCor(item.getString());
	                	}
	                	if (item.getFieldName().equals("categoria")) {
	                		
	                		produtosg.setCategoria(item.getString());
	                	}
	                	if (item.getFieldName().equals("valor_custo")) {
	                		
	                		produtosg.setValor_custo(Float.parseFloat(item.getString()));
	                	}
	                	if (item.getFieldName().equals("valor_venda")) {
	                		
	                		produtosg.setValor_venda(Float.parseFloat(item.getString()));
	                	}
	                	if (item.getFieldName().equals("quantidade")) {
	                		
	                		produtosg.setQuantidade(Integer.parseInt(item.getString()));
	                	}
	                	
	                }
	            }
	            
	        } catch (Exception ex) {
	        	System.out.println("Erro na Alteração:"+ ex); 
	        }
	 
	        } else {
	        	System.out.println("Este Servlet realiza apenas upload de arquivos");
	    }

		// USA A AÇÃO NO DAO QUE BUSCA OS DADOS DO PRODUTO
		ProdutoDAO listar = new ProdutoDAO();
		
		ArrayList<ProdutoSG> lista = listar.buscaTodos();  
		request.setAttribute("lista_produto", lista);
		
		// UTILIZA O METODO DE ALTERAR DO DAO 
		produtodao.alterar(produtosg);
		
		// MENSAGEM DE ALTERADO COM SUCESSO
    	request.setAttribute("mensagem", produtodao.Mensagem);
		
		// EXIBIR TELA APÓS CADASTRAR O PRODUTO
		request.getRequestDispatcher("AlterarProduto.jsp").forward(request, response);
	}
}
