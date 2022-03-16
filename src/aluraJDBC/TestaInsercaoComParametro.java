package aluraJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		
		try(Connection connection = new ConnectionFactory().recuperaConexao()){
			
			connection.setAutoCommit(false);
			
			try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)"
					, Statement.RETURN_GENERATED_KEYS);
					){
				
				adcionarVariavel("SmartTV", "45 Polegadas", stm);
				adcionarVariavel("Radio", "Radio de Bateria", stm);
				
				connection.commit();

			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				connection.rollback();
			}
			
		}
		
	}

	private static void adcionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		
		stm.setString(1,  nome);
		stm.setString(2,  descricao);
		
		if(nome.equals("Radio")) {
			throw new RuntimeException("Não foi possivel add produto");
		}
		
		stm.execute();
		
		try(ResultSet rst = stm.getGeneratedKeys();){
			while(rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id criado foi: " + id);
				}
			}
		}
	}
