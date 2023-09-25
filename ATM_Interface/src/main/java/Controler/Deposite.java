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


@WebServlet("/deposite")
public class Deposite extends HttpServlet 
{
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		 String amount=req.getParameter("amount");
		 double amount1=Double.parseDouble(amount);
		 
		 EntityManagerFactory emf=Persistence.createEntityManagerFactory("ATM");
		 EntityManager em=emf.createEntityManager();
		 EntityTransaction et=em.getTransaction();
		 
		 HttpSession hs=req.getSession();
		 User user1=(User)hs.getAttribute("user");
		 
		   if (user1 != null && user1.getAccountno() != 0) {
	         
	            User existingUser = em.find(User.class, user1.getAccountno());
	            
	            if (existingUser != null) {
	                double currentBalance = existingUser.getBalance();

	                
	               
	                    
	                    existingUser.setBalance(currentBalance + amount1);
	                    existingUser.setDeposite(amount1);

	                    et.begin();
	                    em.merge(existingUser);
	                    et.commit();
	                 
	                    PrintWriter pw=resp.getWriter();
	                    pw.println("Deposite successful. New balance: " + existingUser.getBalance());
	                    RequestDispatcher rd=req.getRequestDispatcher("User_Module.html");
	                    rd.include(req, resp);
	                    resp.setContentType("text/html");
	                   
	                } 
	            }
	        }
		 
		

}
