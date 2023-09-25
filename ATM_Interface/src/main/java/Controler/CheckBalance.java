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

@WebServlet("/check")
public class CheckBalance extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
	    String pin=req.getParameter("key");
	    int pin1=Integer.parseInt(pin);
	    
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATM");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        User u = em.find(User.class, user.getAccountno());
        
        if (u != null) 
        {
 	       
	          int currentPin = u.getPin();
        if(currentPin ==pin1)
		{
        
            if (u != null && u.getAccountno() != 0) 
            {
            User existingUser = em.find(User.class, u.getAccountno());

                if (existingUser != null) 
                {
			        double balance = existingUser.getBalance();
			        
			        PrintWriter pw=resp.getWriter();
			        pw.println("Your account balance: " + balance);
			        RequestDispatcher rd=req.getRequestDispatcher("User_Module.html");
				    rd.include(req, resp);
				    resp.setContentType("text/html");
                }
            }
	    }
        else
        {
        	  PrintWriter pw=resp.getWriter();
			  pw.println("Invalid Pin");
			  RequestDispatcher rd=req.getRequestDispatcher("User_Module.html");
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
