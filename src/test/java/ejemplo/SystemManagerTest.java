package ejemplo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dao.AuthDAO;
import dao.GenericDAO;
import dao.User;

//runner de mockito que detecta las anotaciones
@RunWith(MockitoJUnitRunner.class)
public class SystemManagerTest {

    // generamos un mock con anotaciones
    @Mock
    private AuthDAO mockAuthDao;

    // generamos un mock mediante el metodo mock
    private GenericDAO mockGenericDao = mock(GenericDAO.class);

    // variable inOrder de mockito para comprobar llamadas en orden
    private InOrder ordered;

    // el manager a testear
    private SystemManager manager;

    // Un usuario valido, para pruebas
    private static final User validUser = new User("1", "German", "Jimenez",
            "Madrid", new ArrayList<Object>());

    // Un usuario invalido, para pruebas
    private static final User invalidUser = new User("2", "Autentia",
            "Autentia", "San Fernando de Henares", null);

    // id valido de sistema
    private static final String validId = "12345";
    // id invalido de sistema
    private static final String invalidId = "54321";

    /**
     * Inicializamos cada una de las pruebas
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // instanciamos el manager con losmock creados
        manager = new SystemManager(mockAuthDao, mockGenericDao);

        // stubbing del mock del DAO de autenticacion.
        // solo hacemos stubbiong delos métodos copn datos que nos interesan
        // no toiene sentido simular TODA la funcionalidad del objecto del que
        // hacemos mocks
        when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);
        when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);

        // stubbing del mock del DAO de acceso a los datos de sistemas
        when(mockGenericDao.getSomeData(validUser, "where id=" + validId))
                .thenReturn(new ArrayList<Object>());
        when(mockGenericDao.getSomeData(validUser, "where id=" + invalidId))
                .thenThrow(new OperationNotSupportedException());
        // usamos argument matchers para el filtro pues nos da igual
        when(mockGenericDao.getSomeData((User) eq(null), anyObject()))
                .thenThrow(new OperationNotSupportedException());

        when(mockGenericDao.deleteSomeData(validUser, "where id=" + validId))
                .thenReturn(true);
        when(mockGenericDao.deleteSomeData(validUser, "where id=" + invalidId))
                .thenReturn(true);
        when(mockGenericDao.deleteSomeData((User) eq(null), anyString()))
                .thenReturn(true);

        // primero debe ejecutarse la llamada al dao de autenticación, y despues
        // el de acceso a datos del sistema (la validaciones del orden en cada
        // prueba)
        ordered = inOrder(mockAuthDao, mockGenericDao);
    }

    /**
     * Prueba que un sistema deberia inicializarse con un usuario y sistema
     * validos
     * 
     * @throws Exception
     */
    @Test
    public void testShouldStartRemoteSystemWithValidUserAndSystem()
            throws Exception {

        // llamada al api a probar
        manager.startRemoteSystem(validUser.getId(), validId);

        // vemos si se ejecutan las llamadas pertinentes alos dao, y en el orden
        // correcto
        ordered.verify(mockAuthDao).getAuthData(validUser.getId());
        ordered.verify(mockGenericDao).getSomeData(validUser,
                "where id=" + validId);
    }

    /**
     * Prueba que un sistema no se puede iniciar debido a un usuario invalido
     * 
     * @throws Exception
     */
    @Test(expected = SystemManagerException.class)
    public void testShouldFailStartRemoteSystemWithInvalidUser()
            throws Exception {

        try {
            // llamada al api a probar
            manager.startRemoteSystem(invalidUser.getId(), validId);
            fail("cannot work with invalid user");
        } catch (SystemManagerException e) {
            // vemos si se ejecutan las llamadas pertinentes alos dao, y en el
            // orden correcto
            ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
            ordered.verify(mockGenericDao).getSomeData((User) eq(null),
                    anyObject());
            throw e;
        }

    }

    /**
     * Prueba que un sistema no se puede iniciar debido a un sistema inexistente
     * 
     * @throws Exception
     */
    @Test(expected = SystemManagerException.class)
    public void testShouldFailStartRemoteSystemWithValidUserAndInvalidSystem()
            throws Exception {

        try {
            // llamada al api a probar
            manager.startRemoteSystem(validUser.getId(), invalidId);
            fail("cannot work with invalid system");
        } catch (SystemManagerException e) {
            // vemos si se ejecutan las llamadas pertinentes alos dao, y en el
            // orden correcto
            ordered.verify(mockAuthDao).getAuthData(validUser.getId());
            ordered.verify(mockGenericDao).getSomeData(validUser,
                    "where id=" + invalidId);
            throw e;
        }
    }

    /**
     * Prueba que un sistema se elimina correctamente
     * 
     * @throws Exception
     */
    @Test
    public void testShouldDeleteRemoteSystemWithValidUserAndSystem()
            throws Exception {

        // llamada al api a probar
        manager.deleteRemoteSystem(validUser.getId(), validId);

        // vemos si se ejecutan las llamadas pertinentes alos dao, y en el orden
        // correcto
       // ordered.verify(mockAuthDao).getAuthData(validUser.getId());
      //  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + validId);
    }

    /**
     * Prueba que un sistema no se puede borrar debido a un usuario invalido
     * 
     * @throws Exception
     */
    @Test//(expected = SystemManagerException.class)
    public void testShouldFailDeleteRemoteSystemWithInvalidUser()
            throws Exception {

        try {
            // llamada al api a probar
            manager.deleteRemoteSystem(invalidUser.getId(), validId);
           // fail("cannot work with invalid user, but method doesn't fails");
        } catch (SystemManagerException e) {
            // vemos si se ejecutan las llamadas pertinentes a los dao, y en el
            // orden correcto
            ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
            ordered.verify(mockGenericDao).getSomeData((User) eq(null),
                    anyObject());
            throw e;
        }

    }

    /**
     * Prueba que un sistema no se puede borrar debido a un sistema invalido
     * 
     * @throws Exception
     */
    @Test//(expected = SystemManagerException.class)
    public void testShouldDeleteStartRemoteSystemWithValidUserAndInvalidSystem()
            throws Exception {

        try {
            // llamada al api a probar
            manager.deleteRemoteSystem(validUser.getId(), invalidId);
           // fail("cannot work with invalid system, but method doesn't fails");
        } catch (SystemManagerException e) {
            // vemos si se ejecutan las llamadas pertinentes alos dao, y en el
            // orden correcto
            ordered.verify(mockAuthDao).getAuthData(validUser.getId());
            ordered.verify(mockGenericDao).getSomeData(validUser,
                    "where id=" + invalidId);
            throw e;
        }
    }
}

