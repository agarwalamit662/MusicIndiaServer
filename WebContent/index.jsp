<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.prgguru.jersey.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%
	
	Map mapUpdate = (Map) DBConnection.getUpdateDetails();
	String updatelink = (String) mapUpdate.get("updatelink");
	String indexoflink = (String)mapUpdate.get("indexoflink");
	String version = 	(String)mapUpdate.get("version");
	
	%>
	
	 
	jsp file!
	<a href = "http://localhost:8080/useraccount/rest/songs/update" target = "_blank">UPDATE LINK</a>
	
	<p>Update Link</p>
	Update Link:- <u><%=updatelink %></u><br>
	IndexOflink:- <u><%=indexoflink %></u><br>
	Version:- <u><%=version %></u><br>
	
	<form action="${pageContext.request.contextPath}/UpdateServlet" method="post">
    <p>Update Link       
    <input type="text" name="updatelink" value = "<%=updatelink %>" size = "100" /></p>

    <p>Index OF link        
    <input type="text" name="indexoflink" value = "<%=indexoflink %>"/></p>

    <p>Version       
    <input type="text" name="version" value = "<%=version %>"/></p>
    
    <p>Submit button.
    <input type="submit" name="submit" value="submit" /></p>
    
</form>
	
</body>
</html>