<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>게시글 목록</h1></div>
	<div>
		<form id="frm">
			<select id="key" name="key">
				<option value="all">전체검색</option>
				<option value="title">제목검색</option>
				<option value="content">내용검색</option>
			</select>
			<input type="text" id="val" name="val"> &nbsp;
			<input type="button" value="검색" onclick="Search();">
		</form>
	</div>
	<div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">순번</th>
					<th scope="col">제목</th>
					<th scope="col">작성일자</th>
					<th scope="col">첨부파일명</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${notices }" var="notice">
					<tr>
						<td scope="row">${notice.id }</td>
						<td scope="row">${notice.title }</td>
						<td scope="row">${notice.wdate }</td>
						<td>&nbsp;${notice.fileName }</td>
						<td scope="row">${notice.hit }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	function Search(){  //ajax로 데이터를 검색하는 부분
		let key = frm.key.value;
		let val = frm.val.value;
		let url = "noticeSearch.do";
		fetch(url,{
			method : 'POST',
			headers : {'Content-Type' : 'application/x-www-form-urlencoded'},
			body : 'key='+key+'&val='+val
		})
		.then(res => res.json())  //리턴 값
		.then(json => htmlConvert(json));
	}
	
	function htmlConvert(json) {  //여기서 jsp에 변경할 부분을 작성한다.
		console.log(json);
	}
</script>
</body>
</html>