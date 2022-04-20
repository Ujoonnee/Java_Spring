package com.yedam.app.bank.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	
	@Autowired BankApi api;

	@GetMapping("/accountList")
	public List<AccountVO>getAccountList(BankVO vo) {
		return api.getAccountList(vo); 
	}
	
	@GetMapping("/getBalance")
	public long getBalance(BankVO vo) {
		return api.getBalance(vo);
	}

	@GetMapping("/getTransactionList")
	public List<Map> getTransactionList(BankVO vo) {
		return api.getTransactionList(vo);
	}
	
	@PostMapping(value="/changeAlias", produces = "text/html; charset=utf-8")
	public String changeAlias(@RequestBody Map<String, String> map) {
		System.out.println(map.get("fintechUseNum"));
		return api.changeAlias(map);
	}
	
	//콜백함수로 인증코드가 들어옴
	@RequestMapping("/bankCallback")
	public String bankCallback(String code) {
		//code로 토큰 발급
//			TokenVO vo = BankApi.getToken(code);
		//
		return "";
	}
	
	//사용자 인증요청
	@RequestMapping("/bankAuth")
	public void bankAuth(HttpServletResponse response) throws Exception {
		String redirect_uri= "http://localhost/app/bankCallback";
		String client_id = "DPV8GH7pNOp0GCbyM2APvlzdIsWMeT2G619H7ZDc";
		
		String reqUrl = "https://testapi.openbanking.or.kr/oauth/2.0/authorize";
		String url = reqUrl 
				    +"?response_type=code"
		            +"&client_id="+client_id
				    +"&redirect_uri="+redirect_uri
				    +"&scope=inquiry transfer login"
				    +"&state=12345678901234567890123456789012"
				    +"&auth_type=0";
		response.sendRedirect(reqUrl);
	}
}
