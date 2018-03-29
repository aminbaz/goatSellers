package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import business_logic.models.Authority;
import business_logic.models.Club;
import business_logic.models.SuperAdmin;
import business_logic.models.User;
import service.Login;

class testLogin {

	@Test
	void testConnect() {
		User user = new User(1);
        Login.connect(user);
        assertEquals(Login.getUser(), user);
	}
	
    @Test
    public void testConnect2() {
        User user = new User(1);
        Login.connect(user);
        User user2 = new User(2);
        Login.connect(user2);
        assertEquals(Login.getUser(), user2);
    }

    @Test
    public void testIsClub() {
        User user = new Club(1,"Real","barcelone.png");
        Login.connect(user);
        assertTrue(Login.isClub());
        assertFalse(Login.isSuperAdmin());
        assertFalse(Login.isAuthority());
    }


    @Test
    public void testIsSuperAdmin() {
        User user = new SuperAdmin(1,"test@mail.fr","test",2);
        Login.connect(user);
        assertTrue(Login.isSuperAdmin());
        assertFalse(Login.isClub());
        assertFalse(Login.isAuthority());
    }

    @Test
    public void testIsAuthority() {
        User user = new Authority(1,"test@mail.fr","test",3);
        Login.connect(user);
        assertTrue(Login.isAuthority());
        assertFalse(Login.isClub());
        assertFalse(Login.isSuperAdmin());
    } 

}
