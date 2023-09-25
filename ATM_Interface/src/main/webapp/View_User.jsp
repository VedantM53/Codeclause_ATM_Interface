
<%@page import="java.io.PrintWriter"%>
<%@page import="Entity.User"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

body
{
  color:white;
}

</style>
</head>
<body align="center" bgcolor="black" color="white">
<h1 >Users</h1>

<%
 
 
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("ATM");
    EntityManager em=emf.createEntityManager();
    EntityTransaction et=em.getTransaction();
    Query query=em.createQuery(" select a from User a");
    List<User> user=query.getResultList();
    if(user.size()>0)
    {
    	
 %>
 <table cellpadding="20" align="center" border="4" class="table">
 <th>ID</th>
<th>ACCOUNTNO</th> 
<th>NAME</th> 
<th>CONTACTNO</th> 
<th>ADDRESS</th> 
<th>PASSWORD</th>
<th>PIN</th>  
<th>DELETE</th> 
  
  
<%
 for(User u: user) {
%>
 <tr>
 <td><%=u.getId() %></td>
 <td><%=u.getAccountno()%></td>
 <td><%=u.getName() %></td>
 <td><%=u.getConcno() %></td>
 <td><%=u.getAddress() %></td>
 <td><%=u.getPswd() %></td>
 <td><%=u.getPin() %></td>
 <td><a href="User_Delete.jsp?id1=<%=u.getAccountno()%>">Delete</a></td>
 </tr>
 <%}%>
 
</table>
<% } else {

PrintWriter pw=response.getWriter();
out.print("No Student is Added");

RequestDispatcher rd=request.getRequestDispatcher("Accoutant_Module.html");
rd.include(request, response);

}%>
 
 
 

</body>
</html>