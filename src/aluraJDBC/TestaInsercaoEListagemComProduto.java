package aluraJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import aluraJDBC.dao.ProdutoDAO;
import aluraJDBC.modelo.Produto;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {
		
		Produto comoda = new Produto("Comoda", "Comoda Vertical");
		
		try(Connection connection = new ConnectionFactory().recuperaConexao()){
			
			ProdutoDAO produtoDAO = new ProdutoDAO(connection);
			produtoDAO.salvar(comoda);
			List<Produto> produtos = produtoDAO.listar();
			
			produtos.stream().forEach(lp -> System.out.println(lp));
		}
	}

}
