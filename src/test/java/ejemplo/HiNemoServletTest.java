package ejemplo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.HiNemoServlet;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HiNemoServletTest {
    
	@BeforeClass
	public static void antesAll(){
	  System.out.println("Inicio test principal.");
	 }
	 
	@AfterClass
	public static void despuesAll(){
	  System.out.println("Fin test principal.");
	 }
	 
    @Test
    public void testDoGet() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        ServletOutputStream mockOutput = mock(ServletOutputStream.class);
        
        when(mockRequest.getParameter("UserName")).thenReturn("edy"); 
        when(mockResponse.getOutputStream()).thenReturn(mockOutput);

        new HiNemoServlet().doGet(mockRequest, mockResponse);
        
        verify(mockResponse).setContentType("plain/text");
        verify(mockOutput).println("Hi Nem! edy");
        
    }
}