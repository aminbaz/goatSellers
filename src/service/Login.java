package service;

import business_logic.models.Authority;
import business_logic.models.Club;
import business_logic.models.SuperAdmin;
import business_logic.models.User;

public class Login {
	
	private static User user;
	
    public static User getUser() {
        return Login.user;
    }
	
    public static void connect(User user) {
        Login.user = user;
    }
    
    public static boolean isClub() {
        return (getUser() instanceof Club);
    }
    
    public static boolean isSuperAdmin() {
        return (getUser() instanceof SuperAdmin);
    }
    
    public static boolean isAuthority() {
        return (getUser() instanceof Authority);
    }

}
