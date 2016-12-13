<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; ">
<title>Insert title here</title>
</head>
<body>
	<form action="./sevlet.jsp?action=add" method="post">
		url:<input type="text" name="url" value="http://"> name:<input
			type="text" name="name"> <input type="submit" value="submit">
	</form>
	<form action="./sevlet.jsp?action=delete" method="post">
		<table>
			<thead>
				<tr>
					<td>id</td>
					<td>name</td>
					<td>url</td>
					<td>delete</td>
				</tr>
			</thead>
			<c:forEach var="i" items="${favorites }">
				<tr>
					<td>${i.id }</td>
					<td>${i.name }</td>
					<td><a href="${i.url}">${i.url }</a>&nbsp;</td>
					<td><a
						href="./edit.jsp?id=${i.id }&name=${i.name}&url=${i.url}">edit</a></td>
					<td><input type="checkbox" name="ids" value="${i.id }"/></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="submit">
	</form>
</body>
</html>