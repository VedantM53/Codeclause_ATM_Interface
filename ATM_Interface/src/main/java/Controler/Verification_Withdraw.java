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
import javax.servlet.http.HttpSession;

import Entity.User;
@WebServlet("/key2")
public class Verification_Withdraw extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	   {
		String key=req.getParameter("key");
		int key1=Integer.parseInt(key);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ATM");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		 HttpSession hs=req.getSession();
		 User user1=(User)hs.getAttribute("user");
		 
		 User u = em.find(User.class, user1.getAccountno());
		  
		  if (u != null) {
	       
	          int currentPin = u.getPin();
		  
		  if(currentPin==key1)
		  {
			  PrintWriter pw=resp.getWriter();
			  pw.println("Key Verified");
			  RequestDispatcher rd=req.getRequestDispatcher("Withdraw.html");
			  rd.include(req, resp);
			  resp.setContentType("text/html");
		  }
		  else
		  {
			  PrintWriter pw=resp.getWriter();
			  pw.println("Invalid Credential");
			  RequestDispatcher rd=req.getRequestDispatcher("Key_Withdraw.html");
			  rd.include(req, resp);
			  resp.setContentType("text/html");
		  }
		  } else {
	          PrintWriter pw = resp.getWriter();
	          pw.println("Session expired or user not found");
	          resp.setContentType("text/html");
	      }
	 } 

}

