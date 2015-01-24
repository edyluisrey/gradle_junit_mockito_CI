package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HiNemoServlet extends HttpServlet {
    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("plain/text");
        
        String userName = req.getParameter("UserName");
        
        resp.getOutputStream().println("Hi Nem! "+userName);
    }
}
