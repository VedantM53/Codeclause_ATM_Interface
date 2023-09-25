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

@WebServlet("/user")
public class Add_User extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id=req.getParameter("id1");
		String accno=req.getParameter("id");
		String name=req.getParameter("name");
		String conc=req.getParameter("contact");
		String address=req.getParameter("address");
		String key=req.getParameter("key");
		String pin=req.getParameter("pin");
		
		
		
		int id1=Integer.parseInt(id);
		long accno1=Long.parseLong(accno);
		long concno=Long.parseLong(conc);
		int key1=Integer.parseInt(key);
		int pin1=Integer.parseInt(pin);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ATM");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		User user=new User();
		user.setId(id1);
		user.setAccountno(accno1);
		user.setName(name);
		user.setConcno(concno);
		user.setAddress(address);
		user.setPswd(key1);
		user.setPin(pin1);
		

		  int u=user.getPswd();
		  HttpSession hs=req.getSession();   // to set admin for merchant create httpsession.
		  hs.setAttribute("user", u);
		
	
		et.begin();
		em.persist(user);
		et.commit();
		
		PrintWriter pw=resp.getWriter();
		pw.println("Succesfully Added");
		
		RequestDispatcher rd=req.getRequestDispatcher("User_Login.html");
		rd.include(req, resp);
		resp.setContentType("text/html");
		
	}

}
