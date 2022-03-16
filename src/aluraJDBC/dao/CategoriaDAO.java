package aluraJDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aluraJDBC.modelo.Categoria;
import aluraJDBC.modelo.Produto;

public class CategoriaDAO {
	
	private Connection conncetion;

	public CategoriaDAO(Connection conncetion) {
		this.conncetion = conncetion;
	}
	
	public List<Categoria> listar() throws SQLException{
		List<Categoria> categorias = new ArrayList<>();
		
		System.out.println("Executando a query de listar categoria");
		
		String sql = "SELECT ID, NOME FROM CATEGORIA";
		
		try(PreparedStatement pstm = conncetion.prepareStatement(sql)){
			
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
					categorias.add(categoria);
				}
			}
			
		}
		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		
		System.out.println("Executando a query de listar categoria");
		
		Categoria ultimo = null;
		
		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID";
		
		try(PreparedStatement pstm = conncetion.prepareStatement(sql)){
			
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					if(ultimo==null || !ultimo.getNome().equals(rst.getString(2))) {
						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
						ultimo=categoria;
						categorias.add(categoria);
					}
					Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
					ultimo.addProduto(produto);
				}
			}
			
		}
		return categorias;
	}
	
}
