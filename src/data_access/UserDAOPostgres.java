package data_access;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import business_logic.models.User;

public class UserDAOPostgres extends UserDAO{
	
	PostgresJDBC db;
	
	public UserDAOPostgres(){
		db = new PostgresJDBC();
	}
	
	@Override
	public User createUserById(String mail, String password) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM public.\"User\" WHERE mail LIKE \'"+mail+"\' AND password LIKE \'"+password+"\'";
		ResultSet result=db.makeQuery(query);
		try {
			if(result.next()) {
				User myUser = new User(result.getString("mail"), result.getString("password"));
				return myUser;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
