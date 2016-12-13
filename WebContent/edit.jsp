<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<br>
	${id}el語句衹能獲取attribute裏面的數據，所以這三個數據為空；
	${name }
	${requestScope.url}
	<hr>
	${pageContext.request.queryString}
	<br>
	所以通過pageContext獲取request對象但是沒辦法獲取parameter屬性、
	<br>
	<hr>
	把request裏面的parameter屬性裏面的值放到attribute;<br>
	還有這個不是嚴格的mvc模式，因爲index.jsp沒有經過控制層，
	<% 
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("url", request.getParameter("url"));
	%>
	<form action="./sevlet.jsp?action=edit" method="post">

		<input type="hidden" name="id" value="${id }">
		url:<input type="text" name="url" value="${url }">
		name:<input type="text" name="name" value="${name }" >
		<input type="submit" value="submit">
	</form>
</body>
</html>