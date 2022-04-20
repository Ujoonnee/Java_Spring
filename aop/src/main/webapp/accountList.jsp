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
	<br>
	<h3>잔액</h3>
	<div id="balance"></div>
	<br>
	<h3>거래내역</h3>
	<table border="1">
		<thead>
			<tr>
				<th>거래일시</th>
				<th>입출금구분</th>
				<th>거래구분</th>
				<th>통장인자내용</th>
				<th>거래금액</th>
				<th>거래후잔액</th>
				<th>거래점명</th>
			</tr>
		</thead>
		<tbody id="transactionList"></tbody>
	</table>
	
	<script>
		$.ajax('accountList')
		.done(response => {
			response.forEach(account => {
				const div = $('<div>');
				$('<button>').html('잔액조회').addClass('balance').appendTo(div);
				$('<button>').html('거래내역조회').addClass('transactionList').appendTo(div);
				$('<span>').html(' 은행명: '+ account.bank_name).appendTo(div);
				$('<span>').html(' 계좌번호: '+account.account_num_masked).appendTo(div);
				$('<input>').html(' 계좌별칭: '+account.account_num_masked).val(account.account_alias).appendTo(div);
				$('<button>').html('별칭변경').addClass('alias').appendTo(div);
// 				$('<span>').html(' 계좌유형: '+account.account_type).appendTo(div);
// 				$('<span>').html(' 계좌상태: '+account.account_state).appendTo(div);
				$('<input>').attr('type', 'hidden').val(account.fintech_use_num)
												.appendTo('<span>').appendTo(div);
				
				div.appendTo($('#list'))
			})
		})
		
		// 잔액조회
		$('#list').on('click', '.balance', () => {
			const fintechUseNum = $(event.target).parent().children().last().val();
			
			$.ajax('getBalance?fintechUseNum=' + fintechUseNum)
			.done(response => {
				$('#balance').html(response + '원');
			})
		})
		
		// 거래내역조회
		$('#list').on('click', '.transactionList', () => {
			const fintechUseNum = $(event.target).parent().children().last().val();
			$('#transactionList').html('');
			
			$.ajax('getTransactionList?fintechUseNum=' + fintechUseNum)
			.done(response => {
				response.forEach(account => {
					const tr = $('<tr>');
					$('<td>').html(account.tran_date + ' ' + account.tran_time).appendTo(tr);
					$('<td>').html(account.inout_type).appendTo(tr);
					$('<td>').html(account.tran_type).appendTo(tr);
					$('<td>').html(account.print_content).appendTo(tr);
					$('<td>').html(account.tran_amt).appendTo(tr);
					$('<td>').html(account.after_balance_amt).appendTo(tr);
					$('<td>').html(account.branch_name).appendTo(tr);
					
					tr.appendTo($('#transactionList'));
				})
			})
		})
		
		// 별칭변경
		$('#list').on('click', '.alias', () => {
			const fintechUseNum = $(event.target).parent().children().last().val();
			const alias = $(event.target).prev().val();
			
			$.ajax({
				url: 'changeAlias',
				method: 'post',
				contentType: 'application/json',
				data: JSON.stringify({fintech_use_num: fintechUseNum, account_alias: alias})
			})
			.done(response => {
				if (response == '') response = '변경 완료';
				alert(response)
			})
		})
	</script>
</body>
</html>