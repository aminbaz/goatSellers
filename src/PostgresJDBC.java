import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 
public class PostgresJDBC {
	
	
	
	public void getConnection(){
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 // create three connections to three different databases on localhost
        Connection conn1 = null;
 
        try {
            // Connect method #1
            String dbURL1 = "jdbc:postgresql:java_project?user=postgres&password=postgres";
            conn1 = DriverManager.getConnection(dbURL1);
            if (conn1 != null) {
                System.out.println("Connected to database #1");
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
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
