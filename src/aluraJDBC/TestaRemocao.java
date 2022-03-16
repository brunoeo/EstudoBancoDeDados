package aluraJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		Connection conncetion = new ConnectionFactory().recuperaConexao();
		
		PreparedStatement stm = conncetion.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
		stm.setInt(1, 2);
		
		stm.execute();
		
		Integer linhasModificadas = stm.getUpdateCount();
		System.out.println(linhasModificadas);
		
	}

}
