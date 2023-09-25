package Controler;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entity.User;

@WebServlet("/Login1")
public class User_Login_Validation extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String accno=req.getParameter("accno");
		String key=req.getParameter("key");
		
		long accno1=Long.parseLong(accno);
		int pswd=Integer.parseInt(key);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ATM");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Query query=em.createQuery(" select a from User a where a.accountno=?1 and a.pswd=?2");
		query.setParameter(1, accno1);
		query.setParameter(2, pswd);
		List<User> user=query.getResultList();
		
		if(user.size()>0)
		{
			  User u=user.get(0);
			  HttpSession hs=req.getSession();   
			  hs.setAttribute("user", u);
			PrintWriter pw=resp.getWriter();
			pw.println("Welcome "+u.getName());
			
			RequestDispatcher rd=req.getRequestDispatcher("User_Module.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
		else
		{
			PrintWriter pw=resp.getWriter();
			pw.println("Invalid Credentials");
			
			RequestDispatcher rd=req.getRequestDispatcher("User_Login.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
		
		
		
		
	}
	

}
