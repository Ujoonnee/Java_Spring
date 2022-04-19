package com.yedam.app.bank.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
