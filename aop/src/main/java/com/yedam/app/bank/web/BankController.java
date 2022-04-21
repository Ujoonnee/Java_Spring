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
		return api.changeAlias(map);
	}
	
	//사용자 인증요청
	@RequestMapping("/bankAuth")
	public void bankAuth(HttpServletResponse response) {
		api.bankAuth(response);
	}
	
	//콜백함수로 인증코드가 들어옴
	@RequestMapping("/bankCallback")
	public String bankCallback(String code) {
		api.getToken(code);
		return "";
	}
}
