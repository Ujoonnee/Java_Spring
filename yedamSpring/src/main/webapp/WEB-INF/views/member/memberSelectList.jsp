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
<h1>회원목록</h1>
<div>
	<table border="1">
		<thead>
			<tr>
				<th width="150">아이디</th>
				<th width="150">이 름</th>
				<th width="150">전화번호</th>
				<th width="250">주 소</th>
				<th width="150">권 한</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${members }">
				<tr>
					<td>${member.id }</td>
					<td>${member.name }</td>
					<td>${member.tel }</td>
					<td>${member.address }</td>
					<td>${member.authority }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div>
	<button type="button" onclick="location.href='home.do'">홈 화면</button>	
</div>
</body>
</html>