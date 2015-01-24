package ejemplo;

import static org.junit.Assert.assertEquals;
import interfaces.ITranslator;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class GreetingTest {
	 
    @Test
    public void shouldTestGreetingInItalian(){
        //setup
        ITranslator mockTranslator = mock(ITranslator.class);
        Greeting greeting = new Greeting(mockTranslator);
        stub(mockTranslator.translate("English", "Italian", "Hello")).toReturn("Ciau");
       //execute
        assertEquals("Ciau Paulo", greeting.sayHello("Italian", "Paulo"));
        //verify
        verify(mockTranslator).translate("English", "Italian", "Hello");
    }
}     