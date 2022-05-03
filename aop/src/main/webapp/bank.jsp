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
	<table id="list">
		<tr>
			<td>핀테크이용번호</td><td>출금기관명</td><td>계좌별명</td><td>계좌번호</td>
		</tr>
	</table>
	<br>
	
	<h3>실명인증</h3>
	<table>
		<tr>
			<td>은행</td>
			<td>
				<select id="bank_code_std">
					<option value="004" selected>국민은행</option>
					<option>대구은행</option>
					<option>오픈은행</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>계좌번호</td>
			<td><input id="account_num" value="64200204199049"></td>
		</tr>
		<tr>
			<td>주민번호 앞자리</td>
			<td><input id="account_holder_info" value="960306"></td>
		</tr>
	</table>
	<button id="getRealNameBtn">실명인증하기</button>
	<div id="realName"></div>
	<br>
	
	<h3>출금이체</h3>
	<h4>출금계좌 정보</h4>
	<table>
		<tr>
			<td>요청고객성명</td><td><input id="req_client_name" value="이우준"></td>
		</tr>
		<tr>
			<td>출금계좌번호</td><td><input id="req_client_num" value="64200204199049"></td>
		</tr>
		<tr>
			<td>금액</td><td><input id="trans_amt" value="10000"></td>
		</tr>
	</table>
	<h4>이체할 계좌 정보</h4>
	<table>
		<tr>
			<td>수취고객성명</td><td><input id="recv_client_name" value="이우준"></td>
		</tr>
		<tr>
			<td>은행</td>
			<td>
				<select id="recv_client_bank_code">
					<option value="031">대구은행</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>입금계좌번호</td><td><input id="recv_client_account_num" value="508118364106"></td>
		</tr>
	</table>
	<button id="withdrawBtn">출금이체</button>
	<br>
	<h4>출금이체결과</h4>
	<div id="withdrawResult"></div>
	
	
	
	<script>
	// 문제1 계좌목록조회
		$.ajax('문제1')
		.done(response => {
			response.forEach(account => {
				const tr = $('<tr>');
				$('<td>').html(account.fintech_use_num).appendTo(tr);
				$('<td>').html(account.bank_name).appendTo(tr);
				$('<td>').append($('<input>').val(account.account_alias)).append($('<button>').html('변경').addClass('alias')).appendTo(tr);
				$('<td>').html(account.account_num_masked).appendTo(tr);
				$('<td>').html($('<input>').attr('type', 'hidden').val(account.fintech_use_num)).appendTo(tr);
				
				tr.appendTo($('#list'))
			})
		})
		
	// 문제2 별칭변경
		$('#list').on('click', '.alias', () => {
			const fintechUseNum = $(event.target).parent().next().next().children().first().val();
			const alias = $(event.target).prev().val();
			
			$.ajax({
				url: '문제2',
				method: 'post',
				contentType: 'application/json',
				data: JSON.stringify({fintech_use_num: fintechUseNum, account_alias: alias})
			})
			.done(response => {
				if (response == '') response = '변경 완료';
				alert(response)
			})
		})
		
		
	// 문제3 계좌실명조회
		$('#getRealNameBtn').on('click', () => {
			$.ajax({
				url: '문제3',
				method: 'post',
				contentType: 'application/json',
				data: JSON.stringify({bank_code_std: $('#bank_code_std').val(), account_num: $('#account_num').val(), account_holder_info: $('#account_holder_info').val()})
			})
			.done(response => {
				$('#realName').text('결과: ' + response);
			});
		});
		
	
	// 문제4 출금이체
		$('#withdrawBtn').on('click', () => {
			$.ajax({
				url: '문제4',
				method: 'post',
				contentType: 'application/json',
				data: JSON.stringify({tran_amt: $('#tran_amt').val(), req_client_name: $('#req_client_name').val(), req_client_num: $('#req_client_num').val(), recv_client_name: $('#recv_client_name').val(), recv_client_bank_code: $('#recv_client_bank_code').val(), recv_client_account_num: $('#recv_client_account_num').val()})
			})
			.done(response => console.log(response));
		});
		
	</script>
</body>
</html>