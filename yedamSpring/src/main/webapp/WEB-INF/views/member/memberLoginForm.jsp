<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberLoginForm.jsp</title>
</head>
<body>
	<h1>로그인</h1>

	<form action="memberLogin.do" method="post">
		<table border="1">
			<tr>
				<th width="150">아이디</th>
				<td width="200">
					<input type="email" id="id" name="id">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" id="password" name="password">
				</td>
			</tr>
		</table>
		
		<div>
			<input type="submit" value="로그인">
			<input type="button" onclick="location.href='home.do'" value="홈 화면">
		</div>
	</form>
</body>
</html>