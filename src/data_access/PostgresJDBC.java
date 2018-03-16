package data_access;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
 
public class PostgresJDBC {
	
	private Connection myconnect;
	private static PostgresJDBC db = null;
	
	private PostgresJDBC() {
		getConnection();
	}
	
	public static PostgresJDBC getInstance() {
		if(db == null) {
			db = new PostgresJDBC();
		}
		return db;
	}
	
	public void getConnection(){
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        try {
            // Connect method #1
            String dbURL1 = "jdbc:postgresql:java_project?user=postgres&password=postgres";
            myconnect = DriverManager.getConnection(dbURL1);
            if (myconnect != null) {
                System.out.println("Connected to database #1");
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (myconnect != null && myconnect.isClosed()) {
                    myconnect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	public ResultSet makeQuery(String query) {
		try {
			Statement state = myconnect.createStatement();
			ResultSet result = state.executeQuery(query);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public Connection getMyconnect() {
		return myconnect;
	}

	public void setMyconnect(Connection myconnect) {
		this.myconnect = myconnect;
	}
}
