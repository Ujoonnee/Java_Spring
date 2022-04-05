<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberJoinForm.jsp</title>
</head>
<body>
	<h1>회원가입</h1>
	
	<div>
		<form id="frm" action="memberJoin.do" method="post">
			<table border="1">
				<tr>
					<th width="150">아이디</th>
					<td width="300">
						<input type="email" id="id" name="id">
						<span><input type="button" onclick="idCheck()" value="중복체크"></span>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" id="password" name="password">
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" id="pwd" name="pwd">
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" id="name" name="name">
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" id="tel" name="tel">
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" id="address" name="address">
					</td>
				</tr>
			</table>
			
			<div>
				<input type="submit" value="회원가입">
				<input type="reset" value="취 소">
				<input type="button" onclick="location.href='home.do'" value="홈 화면">
			</div>
		</form>
	</div>
	
	<script>
		function idCheck() {
			const id = frm.id.value;
			const password = frm.password.value;
			
			fetch('ajaxIdCheck.do?', {
				method: 'post',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				body: 'id='+ id
			})
			.then(response => response.text())
			.then(result => {
				if (result == 'yes') {
					alert('이미 존재하는 아이디 입니다.');
					frm.id.value = '';
					frm.id.focus();
				} else {
					alert('사용 가능한 아이디 입니다.');
					frm.password.focus();
				}
				
			})
		}
	</script>
</body>
</html>