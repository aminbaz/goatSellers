package data_access;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import business_logic.models.Authority;
import business_logic.models.Club;
import business_logic.models.SuperAdmin;
import business_logic.models.User;

public class UserDAOPostgres extends UserDAO{

	PostgresJDBC db;

	public UserDAOPostgres(){
		db = PostgresJDBC.getInstance();
	}

	@Override
	public User createUserById(String mail, String password) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM public.\"User\" WHERE mail LIKE \'"+mail+"\' AND password LIKE \'"+password+"\'";
		ResultSet result=db.makeQuery(query);
		try {
			if(result.next()) {
				User myUser = getRoleUser(new User(result.getInt("id_user"), result.getString("mail"), result.getString("password"), result.getInt("role")));
				return myUser;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getRoleUser(User user) {
		boolean find=false;
		User speUser=user;
		String query="SELECT c.id_club, c.name, c.logo, r.id_role, c.blocked FROM public.\"Club\" c, public.\"Role\" r WHERE c.role=r.id_role AND r.rolename LIKE \'Club\' AND c.role="+user.getRole();
		ResultSet result=db.makeQuery(query);
		try {
			if(result.next()) {
				speUser = new Club(user.getId(),user.getMail(),user.getPassword(),user.getRole(),result.getInt("id_club"),result.getString("name"),result.getString("logo"),result.getBoolean("blocked"));
				find=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(find==false) {
			query="SELECT a.id_authority, a.name, r.id_role FROM public.\"Authority\" a, public.\"Role\" r WHERE a.role=r.id_role AND r.rolename LIKE \'Authority\' AND a.role="+user.getRole();
			result=db.makeQuery(query);	
			try {
				if(result.next()) {
					speUser = new Authority(user.getId(),user.getMail(),user.getPassword(),user.getRole(),result.getInt("id_authority"),result.getString("name"));
					find=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(find==false) {
			query="SELECT r.id_role FROM public.\"Role\" r WHERE r.rolename LIKE \'SuperAdmin\' AND r.id_role="+user.getRole();
			result=db.makeQuery(query);	
			try {
				if(result.next()) {
					speUser = new SuperAdmin(user.getId(),user.getMail(),user.getPassword(),user.getRole());
					find=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return speUser;
	}

}
