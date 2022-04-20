package com.yedam.app;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yedam.app.bank.web.AccountList;
import com.yedam.app.bank.web.BankApi;
import com.yedam.app.bank.web.BankVO;

public class BankTest {

	String use_org_code = "M202200732";
	String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1ODU3Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NTgwNDY0NjUsImp0aSI6ImRlNDAwODZjLWE4OTQtNDFlNC05MWIwLWY5ZDBiY2IwZTZjNCJ9.q7MnHT4JI9h9Z7K4faEFNkbdgvl9dLVH9SEhSYQakvg";
	String oob_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJNMjAyMjAwNzMyIiwic2NvcGUiOlsib29iIl0sImlzcyI6Imh0dHBzOi8vd3d3Lm9wZW5iYW5raW5nLm9yLmtyIiwiZXhwIjoxNjU4MTkwMTE1LCJqdGkiOiJlODVkNjU3NC0wNjgzLTQwMWItYWNjOS0xYmY4YzAxMWU1YzMifQ.GcJuNXpM5c1O9k45Qvtg5oArzbpi8fWGqZdp4urYkmA";

//	@Test
	public void getBalance() {
		BankVO vo = new BankVO();
		
		long balance = 0L;

		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num";

		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		String millis = String.valueOf(System.currentTimeMillis());
		String param = "bank_tran_id=M202200732U" + millis.substring(4);
		param += "&tran_dtime=" + now;
		param += "&fintech_use_num=" + vo.getFintechUseNum();

		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Bearer " + accessToken);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request, Map.class);

		Map json = response.getBody();
		balance = Long.valueOf((String) json.get("balance_amt"));
		
		System.out.println(balance);
	}

//	@Test
	public void getAccountList() {
		
		BankVO vo = new BankVO();
		
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/list";

		String param = "user_seq_no=" + vo.getUserSeqNo();
		param += "&include_cancel_yn=Y&sort_order=D";

		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Bearer " + vo.getAccessToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);

		RestTemplate restTemplate = new RestTemplate();

		// VO 생성
		ResponseEntity<AccountList> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request, AccountList.class);
		
		System.out.println(response.getBody().getRes_list());
	}

//	@Test
	public void getRealName() {
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/inquiry/real_name";

		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		String millis = String.valueOf(System.currentTimeMillis());
		
		Map<String, String> param = new HashMap<>();
		param.put("bank_tran_id", use_org_code + "U" + millis.substring(4));
		param.put("bank_code_std", "004");
		param.put("account_num", "64200204199049");
		param.put("account_holder_info_type", " ");
		param.put("account_holder_info", "960306");
		param.put("tran_dtime", now);
		
		ObjectMapper om = new ObjectMapper();
		String str = "";
		try {
			str = om.writeValueAsString(param);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		
		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Bearer " + oob_token);
		headers.set("Content-Type", "application/json; charset=UTF-8");

		HttpEntity<String> request = new HttpEntity<String>(str, headers);

		RestTemplate restTemplate = new RestTemplate();

//		ResponseEntity<String> response = restTemplate.exchange(reqURL, HttpMethod.POST, request, String.class);
//		String realName = response.getBody();
		
		Map response = restTemplate.postForObject(reqURL, request, Map.class);
		String realName = response.get("account_holder_name").toString(); 
		
		System.out.println(realName);
	}

//	@Test
	public void getTransactionList() {
		BankVO vo = new BankVO();
		
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num";

		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		String millis = String.valueOf(System.currentTimeMillis());
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("bank_tran_id", use_org_code + "U" + millis.substring(4));
		param.add("fintech_use_num", vo.getFintechUseNum());
		param.add("inquiry_type", "A");
		param.add("inquiry_base", "D");
		param.add("from_date", "20020301");
		param.add("to_date", "20020302");
		param.add("sort_order", "D");
		param.add("tran_dtime", now);
		
		URI uri = UriComponentsBuilder.fromUriString(reqURL)
				.queryParams(param)
				.build().encode()
				.toUri();

		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Bearer " + vo.getAccessToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.GET, request, Map.class);
		
		List list = (List) response.getBody().get("res_list");
		System.out.println(list);
	}
	
//	@Test
	public void changeAlias() {
		Map<String, String> map = new HashMap<>();
		map.put("fintech_use_num", "120220073288941057395017");
		map.put("account_alias", "바뀐 별명");
		
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/update_info";
		
		HttpHeaders headers = new HttpHeaders();
		String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1ODU3Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NTgwNDY0NjUsImp0aSI6ImRlNDAwODZjLWE4OTQtNDFlNC05MWIwLWY5ZDBiY2IwZTZjNCJ9.q7MnHT4JI9h9Z7K4faEFNkbdgvl9dLVH9SEhSYQakvg";
		
		headers.set("Authorization", "Bearer "+ accessToken);
		headers.set("Content-Type", "application/json; charset=UTF-8");

		ObjectMapper om = new ObjectMapper();
		String str = "";
		try {
			str = om.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		HttpEntity<String> request = new HttpEntity<String>(str, headers);

		RestTemplate restTemplate = new RestTemplate();

		Map response = restTemplate.postForObject(reqURL, request, Map.class);
		System.out.println(response);
	}
	
	@Test
	public void bankAuth() {
		new BankApi().b
	}
	
	@Test
	public void getToken() {
		
		String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("code", "");
		map.add("client_id", "bc9288c2-0b3c-423e-a3e2-15ab8abd8e99");
		map.add("client_secret", "de213564-0573-4855-9569-a8f044014d6a");
		map.add("redirect_uri", "http://localhost/html/callback.html");
		map.add("grant_type", "");
		
	}
}
