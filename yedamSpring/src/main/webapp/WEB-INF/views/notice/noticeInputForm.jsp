<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 등록</h1>
	
	<form action="noticeInsert.do" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th width="70">제목</th>
				<td width="300">
					<input type="text" id="title" name="title">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="100" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>작성일자</th>
				<td>
					<input type="date" id="wdate" name="wdate">
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" id="file" name="file">
				</td>
			</tr>
		</table>
		
		<input type="submit" value="글 등록">
		<input type="reset" value="취소">
		<input type="button" onclick="location.href='home.do'" value="홈 화면">
	</form>

</body>
</html>