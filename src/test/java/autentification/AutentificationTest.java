package autentification;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import autentificattion.auth.AuthHashMD5;
import autentificattion.auth.AuthManager;
import autentificattion.auth.AuthPlain;
import autentificattion.auth.AuthStrategy;
import autentificattion.auth.Base64Coder;
import autentificattion.dao.UserDao;
import autentificattion.model.User;

//Un mock de una interfaz nos sirve para confirmar que los métodos de la interfaz se han llamado correctamente durante la ejecución del test.
//El test graba en los mocks los métodos a los que se les debe llamar y lo que deben responder
//Por último se verifica que los mocks recibieron todas las llamadas que debian recibir con el método verify.
public class AutentificationTest {
	private static final String USERNAME = "test username";
	private static final String SUPPLIED_PASS = "suple password";
	private static final String STORED_PASS = "store password";
	
	UserDao userDao = mock(UserDao.class);
	AuthStrategy authStrategy = mock(AuthStrategy.class);
	
	
	@Test
	public void testAuthManagerValidate() throws Exception {
		  AuthManager authManager = newAuthManager();
		  when(userDao.findByUsername(USERNAME)).thenReturn(newTestUser());
	      when(authStrategy.validate(SUPPLIED_PASS,STORED_PASS)).thenReturn(true);
	      
	      //metodo a testear
	      authManager.validate(USERNAME, SUPPLIED_PASS);
	      
	     verify(userDao).findByUsername(USERNAME);
	     verify(authStrategy).validate(SUPPLIED_PASS,STORED_PASS);
    }
	
	private User newTestUser() {
		User user = new User();
		user.setPassword(STORED_PASS);
		user.setUsername(USERNAME);
		return user;
	}
    
	
	/**
	 * @return new auth manager
	 */
	private AuthManager newAuthManager() {
		AuthManager authManager = new AuthManager();
		reset(authStrategy);
		authManager.setAuthStrategy(authStrategy);
		reset(userDao);
		authManager.setUserDao(userDao);
		
		return authManager;
	}
	
	/**
	 * Test Auth plain
	 */
	@Test
	public void testAuthPlain()  {
		AuthPlain auth = new AuthPlain();
		assertTrue(auth.validate(SUPPLIED_PASS, SUPPLIED_PASS));
		assertFalse(auth.validate(SUPPLIED_PASS, STORED_PASS));
		assertFalse(auth.validate(null, null));
	}
	
	@Test
    public void testAddition() {
        assertEquals(4, 2 + 2);
    }
	
	
	/**
	 * Test auth hash md5
	 */
	@Test
	public void testAuthHashMD5() {
		AuthHashMD5 auth = new AuthHashMD5();
		try {
			assertTrue(auth.validate(SUPPLIED_PASS, hashmd5(SUPPLIED_PASS)));
		}
		catch (NoSuchAlgorithmException e) {
			fail(e.getMessage());
		}
		assertFalse(auth.validate(SUPPLIED_PASS, STORED_PASS));
		assertFalse(auth.validate(null, null));
	}
	
	
	/**
	 * @param asuppliedPass pass
	 * @return base64 encode of hash md5
	 * @throws NoSuchAlgorithmException from MessageDigester
	 */
	private String hashmd5(String asuppliedPass) throws NoSuchAlgorithmException   {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(asuppliedPass.getBytes());
	
		return String.valueOf(Base64Coder.encode(md.digest()));
	}

}
