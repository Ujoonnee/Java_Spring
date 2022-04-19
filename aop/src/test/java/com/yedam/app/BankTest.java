package com.yedam.app;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.yedam.app.bank.web.AccountList;
import com.yedam.app.bank.web.BankApi;
import com.yedam.app.bank.web.BankVO;

public class BankTest {

//	@Autowired BankApi api;

	@Test
	public void getBalance() {
		BankApi api = new BankApi();
		BankVO vo = new BankVO();
		long balance = api.getBalance(vo);
		System.out.println(balance);
	}

	@Test
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
	}
}
