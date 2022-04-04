<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberSelectList.jsp</title>
</head>
<body>
	<c:forEach var="member" items="${members }">
		${member.name }
		
	</c:forEach>
</body>
</html>