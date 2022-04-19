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
	<h3>계좌리스트</h3>
	<div id="list"></div>
	
	<script>
		$.ajax('accountList')
		.done(response => {
			response.forEach(account => {
				const div = $('<div>')
				$('<button>').html('잔액조회').appendTo(div);
				$('<span>').html(' 은행명: '+ account.bank_name).appendTo(div);
				$('<span>').html(' 계좌번호: '+account.account_num_masked).appendTo(div);
				$('<span>').html(' 계좌유형: '+account.account_type).appendTo(div);
				$('<span>').html(' 계좌상태: '+account.account_state).appendTo(div);
				$('<input>').attr('type', 'hidden').val(account.fintech_use_num).appendTo('<span>')
																		.appendTo(div)
				
				div.appendTo($('#list'))
			})
		})
		
		$('#list').on('click', 'button', () => {
			const fintechUseNum = $(event.target).parent().children().last().val();
			
			$.ajax('getBalance?fintechUseNum=' + fintechUseNum)
			.done(response => {
				alert(response + '원');
			})
		})
	</script>
</body>
</html>