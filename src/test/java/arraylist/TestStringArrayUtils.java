package arraylist;

import java.util.Vector;

import junit.framework.Assert;
import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;


/** 
 * Realiza pruebas funcionales sobre la clase
 */  
public class TestStringArrayUtils {  
  
    private static StringArrayUtils stringUtils;  
  
    /** 
     * Leemos desde un fichero, los datos de prueba con los que vamos a realizar las pruebas 
     */  
    @BeforeClass  
    public static void inicioClase() {  
        Vector<String> velements = new Vector<String>();   
        velements.addElement("one");  
        velements.addElement("two");  
        velements.addElement("five");   
        
        // Creamos el String[] a partir de los datos leidos del fichero  
        String[] elements = new String[velements.size()];  
        velements.copyInto(elements);  
        TestStringArrayUtils.stringUtils = new StringArrayUtils(elements);  
  
        // Liberamos recursos  
        velements.removeAllElements();       
    }  
      
    /** 
     * Este m�todo liberar�a los recursos reservados en BeforeClass 
     */  
    @AfterClass  
    public static void finClase() {  
        // Para este ejemplo no hacemos nada, pero exponemos el m�todo por  
        // motivos did�cticos exclusivamente  
    }  
  
    /** 
     * Este m�todo se ejecuta para cada prueba ANTES de invocar el c�digo de cada prueba 
     */  
    @Before  
    public void testStart() {  
        // Para este ejemplo no hacemos nada, pero exponemos el m�todo por  
        // motivos did�cticos exclusivamente  
    }  
  
    /** 
     * Este m�todo se ejecuta para cada prueba DESPU�S de invocar el c�digo de cada prueba. 
     */  
    @After  
    public void testEnd() {  
        // Para este ejemplo no hacemos nada, pero exponemos el m�todo por  
        // motivos didacticos exclusivamente  
    }  
  
    /** 
     * Verificamos que en caso de recibir un null como argumento en el 
     * constructor la clase lanza una IllegalArgumentException 
     */  
    @Test(expected = java.lang.IllegalArgumentException.class)  
    public void initTest() {  
        new StringArrayUtils(null);  
    }  
  
    /** 
     * Verificamos que la cadena m�s larga sea la cadena "five" 
     */  
    @Test  
    public void getLengthTest() {  
        Assert.assertEquals("five", TestStringArrayUtils.stringUtils.getMaxLength());  
    }  
  
    /** 
     * Prueba sobre el m�todo que devuelve la suma total de todas las cadenas 
     * almacenadas Suponemos que el calculo del tama�o total es un m�todo 
     * cr�tico que debe realizarse antes de 25 milisegundos 
     */  
    @Test(timeout = 50)  
    public void getTotalLengthTest() {  
        Assert.assertEquals(10, TestStringArrayUtils.stringUtils.getTotalLength());  
    }  
  
    /** 
     * Prueba sobre el m�todo que devuelve la posici�n de una cadena. 
     * Verificamos que si le pasamos null como argumento lanza la excepci�n 
     * correcta 
     */  
    @Test(expected = java.lang.IllegalArgumentException.class)  
    public void getIndexOfTest() {  
        TestStringArrayUtils.stringUtils.getIndexOf(null);  
    }  
  
    /** 
     * Prueba sobre el m�todo que devuelve la posici�n de una cadena Verificamos 
     * que si le pasamos una cadena que no existe como argumento lanza la 
     * excepci�n correcta 
     */  
    @Test(expected = java.util.NoSuchElementException.class)  
    public void getIndexOfTest2() {  
        Assert.assertEquals(0, TestStringArrayUtils.stringUtils.getIndexOf("dont exist"));  
    }  
  
    /** 
     * Prueba sobre el m�todo que devuelve la posici�n de una cadena.  
     * Verificamos que si le pasamos una cadena que existe devuelve la posici�n correcta 
     */  
    @Test  
    public void getIndexOfTest3() {  
        Assert.assertEquals(1, TestStringArrayUtils.stringUtils.getIndexOf("two"));  
    }  
  
    @Ignore("Este test no se hace, se expone como ejemplo")  
    @Test  
    public void ignore() {  
        // C�digo que compone la prueba  
        // ...  
        // ...  
    }  
  
    /** 
     * @return Para mantener compatibilidad con las herramientas que tratan con versiones anteriores de JUnit 
     */  
    public static junit.framework.Test suite() {  
        return new JUnit4TestAdapter(TestStringArrayUtils.class);  
    }  
  
    /** 
     * Lanza las pruebas sin compatibilidad hacia atr�s, es decir se requiere Java 5 y JUnit4 instalado 
     */  
    public static void main(String[] args) {  
        JUnitCore.runClasses(TestStringArrayUtils.class);  
    }  
} 