package aluraJDBC;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPoolDataSource = new ComboPooledDataSource();
		comboPoolDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPoolDataSource.setUser("root");
		comboPoolDataSource.setPassword("14159265");
		
		comboPoolDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPoolDataSource;
	}

	public Connection recuperaConexao() throws SQLException {
		return this.dataSource.getConnection();
	}
}
