package com.yedam.app.bank.web;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BankApi {
	
	String use_org_code = "M202200732";
	String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1ODU3Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NTgwNDY0NjUsImp0aSI6ImRlNDAwODZjLWE4OTQtNDFlNC05MWIwLWY5ZDBiY2IwZTZjNCJ9.q7MnHT4JI9h9Z7K4faEFNkbdgvl9dLVH9SEhSYQakvg";
	String oob_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJNMjAyMjAwNzMyIiwic2NvcGUiOlsib29iIl0sImlzcyI6Imh0dHBzOi8vd3d3Lm9wZW5iYW5raW5nLm9yLmtyIiwiZXhwIjoxNjU4MTkwMTE1LCJqdGkiOiJlODVkNjU3NC0wNjgzLTQwMWItYWNjOS0xYmY4YzAxMWU1YzMifQ.GcJuNXpM5c1O9k45Qvtg5oArzbpi8fWGqZdp4urYkmA";

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

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);

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

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);

		RestTemplate restTemplate = new RestTemplate();

		// VO 생성
		ResponseEntity<AccountList> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request, AccountList.class);

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
	
	
	public String getRealName() {
		
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
		return realName;
	}
	
	public List<Map> getTransactionList(BankVO vo) {
		
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num";

		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		String millis = String.valueOf(System.currentTimeMillis());
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("bank_tran_id", use_org_code + "U" + millis.substring(4));
		param.add("fintech_use_num", vo.getFintechUseNum());
		param.add("inquiry_type", "A");
		param.add("inquiry_base", "D");
		param.add("from_date", "20220415");
		param.add("to_date", "20220420");
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
		
		return list;
	}
	
	public String changeAlias(Map<String, String> map) {
		
		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/update_info";
		
		HttpHeaders headers = new HttpHeaders();
		
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
		String message = (String) response.get("rsp_message");
		
		return message;
	}
	
}
