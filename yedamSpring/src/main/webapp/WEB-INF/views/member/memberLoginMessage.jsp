<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberLoginMessage.jsp</title>
</head>
<body>
<h1>${message }</h1>
세션 : ${name } ${authority } ${id }

<br>
<a href="memberLogout.do">로그아웃</a>
</body>
</html>