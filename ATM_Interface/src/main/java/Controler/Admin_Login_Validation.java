package Controler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Admin_Login_Validation extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id=req.getParameter("id");
		String key=req.getParameter("key");
		
		int key1=Integer.parseInt(key);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ATM");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		if(id.equalsIgnoreCase("Vedant") && key1==1243)
		{
			PrintWriter pw=resp.getWriter();
			pw.println("LOGIN-SUCCESFULL...");
			RequestDispatcher rd=req.getRequestDispatcher("Admin_Page.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
		else
		{
			PrintWriter pw=resp.getWriter();
			pw.println("INVALID-CREDENTIALS");
			RequestDispatcher rd=req.getRequestDispatcher("Admin_login.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
	}
	

}
