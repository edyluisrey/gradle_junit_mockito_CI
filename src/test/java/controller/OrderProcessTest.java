package controller;

import java.io.IOException;
import java.security.Principal;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
 
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
 
@RunWith(MockitoJUnitRunner.class)
public class OrderProcessTest {
 
 private final String USER = "User";
 private final String ROLE = "Manager";
  
 @Mock
 private HttpServletRequest request;
  
 @Mock
 private HttpServletResponse response;
  
 @Mock
 private Principal principal;
  
 @Test
 public void processTest() throws ServletException, IOException
 {  
  OrderProcess process = new OrderProcess();
   
  when(principal.getName()).thenReturn(USER);
  when(request.getUserPrincipal()).thenReturn(principal);
  when(request.isUserInRole(ROLE)).thenReturn(true);
   
  process.doGet(request, response);
   
  assertTrue(process.isManager());
  assertEquals(process.getLoggedUserName(), USER);
 }
}