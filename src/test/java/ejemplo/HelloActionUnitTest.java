package ejemplo;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 


import org.junit.Before;
import org.junit.Test;
 

import ejemplo.HelloApplication.Greeter;
import ejemplo.HelloApplication.HelloActable;
import ejemplo.HelloApplication.HelloAction;

// Unit test for HelloActable component may look like this:
public class HelloActionUnitTest {
 
   Greeter helloGreeterMock;
   Appendable helloWriterMock;
   HelloActable helloAction;
 
   @Before
   public void setUp() {
      helloGreeterMock = mock(Greeter.class);
      helloWriterMock = mock(Appendable.class);
      helloAction = new HelloAction(helloGreeterMock, helloWriterMock);
   }
 
   @Test
   public void testSayHello() throws Exception {
      when(helloWriterMock.append(any(String.class))).thenReturn(helloWriterMock);
      when(helloGreeterMock.getIntroduction(eq("unitTest"))).thenReturn("unitTest : ");
      when(helloGreeterMock.getGreeting(eq("world"))).thenReturn("hi world");
 
      helloAction.sayHello("unitTest", "world");
      
      verify(helloGreeterMock).getIntroduction(eq("unitTest"));
      verify(helloGreeterMock).getGreeting(eq("world"));
 
      verify(helloWriterMock, times(2)).append(any(String.class));
      verify(helloWriterMock, times(1)).append(eq("unitTest : "));
      verify(helloWriterMock, times(1)).append(eq("hi world"));
   }
}
