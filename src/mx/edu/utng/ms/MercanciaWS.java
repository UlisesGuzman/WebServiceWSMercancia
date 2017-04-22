package mx.edu.utng.ms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class MercanciaWS {

	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement ps;
	
	public MercanciaWS() {
		conectar();
	}
	
	private void conectar(){
		
		try {
			Class.forName("org.postgresql.Driver");
			 connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/wstst",
					"postgres","urito12");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public int addMercancia(Mercancia mercancia){
		int result = 0;
		if(connection!=null){
			try {
				ps = connection.prepareStatement(
						"INSERT INTO mercancia (impuesto)"
						+"VALUES (?);");
				ps.setInt(1, mercancia.getImpuesto());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int editMercancia(Mercancia mercancia){
		int result = 0;
		if(connection!=null){
			try {
				ps = connection.prepareStatement(
						"UPDATE mercancia   SET  impuesto=? WHERE id=?;");
						
				ps.setInt(1, mercancia.getImpuesto());
				ps.setInt(2, mercancia.getId());
				
				result = ps.executeUpdate();
			} catch (SQLException e) {
		e.printStackTrace();
			}
		}
		return result;
	}
	
	public int removeMercancia(int id){
		int result = 0;
		if(connection!=null){
			try {
				ps = connection.prepareStatement(
						"DELETE FROM mercancia WHERE id =?;");
				ps.setInt(1, id);
				result = ps.executeUpdate();
			} catch (SQLException e) {
		e.printStackTrace();
			}
		}
		return result;
	}
	
	public Mercancia[] getMercancias(){
		Mercancia[] result = null;
		List<Mercancia> mercancias = new ArrayList<Mercancia>();
		
		if(connection!=null){
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(
						"SELECT * FROM mercancia;");
				while(resultSet.next()){
					Mercancia mercancia = new Mercancia(
							resultSet.getInt("id"),
							resultSet.getInt("impuesto"));
					
					mercancias.add(mercancia);
				
				}
			} catch (SQLException e) {
		e.printStackTrace();
			}
		}
		result = mercancias.toArray(new Mercancia[mercancias.size()]);
		return result;
	}
	
	public Mercancia getMercanciaById(int id){
		Mercancia mercancia = null;
		
		if(connection!=null){
			try {
				ps = connection.prepareStatement("SELECT * FROM mercancia WHERE id= ?");
				ps.setInt(1, id);
				resultSet = ps.executeQuery();
						
				if(resultSet.next()){
					mercancia = new Mercancia(
							resultSet.getInt("id"),
							resultSet.getInt("impuesto"));
				
				}
			} catch (SQLException e) {
		e.printStackTrace();
			}
		}
		return mercancia;
	}
}

