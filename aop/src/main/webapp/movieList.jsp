<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h3>영화목록</h3>
	<div id="list"></div>
	
	<script>
		$.ajax('firstMovieNm')
		.done(response => {
			$('#list').html(response);
		})
	</script>
</body>
</html>