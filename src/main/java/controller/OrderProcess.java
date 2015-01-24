package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class OrderProcess
 */

public class OrderProcess extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String loggedUserName = "";
    private boolean isManager = false;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProcess() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    java.security.Principal principal = request.getUserPrincipal();
 
        if( principal != null )
            setLoggedUserName(principal.getName());
 
        setManager(request.isUserInRole("Manager"));
 
        if (isManager())
            System.out.println("USER ALLOWED");
        else
            System.out.println("USER NOT ALLOWED");
    }
 
    public String getLoggedUserName() {
        return loggedUserName;
    }
 
    public void setLoggedUserName(String loggedUserName) {
        this.loggedUserName = loggedUserName;
    }
 
    public boolean isManager() {
        return isManager;
    }
 
    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }
}
