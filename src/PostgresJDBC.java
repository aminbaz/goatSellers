import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 
public class PostgresJDBC {
	
	public Connection myconnect;
	
	public PostgresJDBC() {
		getConnection();
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
	
	public static void main(String[] args) {
		PostgresJDBC mypg = new PostgresJDBC();
		mypg.getConnection();
	}
}
