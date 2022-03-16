package aluraJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = new ConnectionFactory().recuperaConexao();
		
		System.out.println("Fechando conexão!!");
		
		connection.close();
		
	}
	
	
	
}
