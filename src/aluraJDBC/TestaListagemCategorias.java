package aluraJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import aluraJDBC.dao.CategoriaDAO;
import aluraJDBC.modelo.Categoria;
import aluraJDBC.modelo.Produto;

public class TestaListagemCategorias {

	public static void main(String[] args) throws SQLException {
		
		try(Connection connection = new ConnectionFactory().recuperaConexao()){
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaDeCategorias = categoriaDAO.listarComProdutos();
			listaDeCategorias.stream().forEach(ct -> {
				System.out.println(ct);
				for(Produto produto : ct.getProdutos()) {
					System.out.println(ct.getNome() + " - " + produto.getNome()); 
				}
			});
			
		}
		
	}

}
