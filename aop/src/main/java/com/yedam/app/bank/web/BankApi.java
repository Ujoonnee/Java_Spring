package com.yedam.app.bank.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class BankApi {

	public long getBalance(BankVO vo) {
		long balance = 0L;

		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num";

		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		String millis = String.valueOf(System.currentTimeMillis());
		String param = "bank_tran_id=M202200732U" + millis.substring(4);
		param += "&tran_dtime=" + now;
		param += "&fintech_use_num=" + vo.getFintechUseNum();

		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Bearer " + vo.getAccessToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null,
				headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request, Map.class);

		Map json = response.getBody();
		balance = Long.valueOf((String) json.get("balance_amt"));
		return balance;
	}

	
	public List<AccountVO> getAccountList(BankVO vo) {

		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/list";

		String param = "user_seq_no=" + vo.getUserSeqNo();
		param += "&include_cancel_yn=Y&sort_order=D";

		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Bearer " + vo.getAccessToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null,
				headers);

		RestTemplate restTemplate = new RestTemplate();

		// VO 생성
		ResponseEntity<AccountList> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request,
				AccountList.class);

//		// VO 생성 X
//		ResponseEntity<String> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request, String.class);
//		
//		ObjectMapper om = new ObjectMapper();
//		
//		try {
//			JsonNode json= om.readTree(response.getBody());
//			System.out.println(json.get("res_list").get(0));
//			System.out.println(json.get("res_list").get(0).get("bank_name"));
//			System.out.println(json.get("res_list").get(0).get("account_num_masked"));
//			System.out.println(json.get("res_list").get(0).get("account_state"));
//			System.out.println(json.get("res_list").get(0).get("account_type"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return response.getBody().getRes_list();
	}
}
