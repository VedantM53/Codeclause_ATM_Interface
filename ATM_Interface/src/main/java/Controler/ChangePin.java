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
@WebServlet("/pin")
public class ChangePin extends HttpServlet 
{
	  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	  {
		
		    String currentPin1= req.getParameter("pin");
	        String newPin1= req.getParameter("newpin");
	        String confirmNewPin1= req.getParameter("confirmpin");
	        
	        int currentPin=Integer.parseInt(currentPin1);
	        int newPin=Integer.parseInt(newPin1);
	        int confirmNewPin=Integer.parseInt(confirmNewPin1);

	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATM");
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction et = em.getTransaction();

	        HttpSession session = req.getSession();
	        User user = (User) session.getAttribute("user");

	        if (user != null && user.getAccountno() != 0) {
	            User existingUser = em.find(User.class, user.getAccountno());

	            if (existingUser != null) {
	                int currentStoredPin = existingUser.getPin();

	                if (currentPin==currentStoredPin && newPin==confirmNewPin) 
	                {
	                    
	                    existingUser.setPin(newPin);;

	                    et.begin();
	                    em.merge(existingUser);
	                    et.commit();

	                  PrintWriter pw=resp.getWriter();
	                  pw.println("PIN changed successfully.");
	                  RequestDispatcher rd=req.getRequestDispatcher("User_Module.html");
	                  rd.include(req, resp);
	                  resp.setContentType("text/html");
	                } 
	                else 
	                {
	                	 PrintWriter pw=resp.getWriter();
	                    pw.println("Invalid PIN or new PIN and confirm PIN do not match.");
	                    RequestDispatcher rd=req.getRequestDispatcher("User_Module.html");
		                  rd.include(req, resp);
		                  resp.setContentType("text/html");
	                }
	            }
	        }
		  
		  
	  }
}







