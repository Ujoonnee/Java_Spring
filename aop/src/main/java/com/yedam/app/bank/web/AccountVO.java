package com.yedam.app.bank.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountVO {
	private String fintech_use_num;
	private String bank_name;
	private String account_num_masked;
	private String account_type;
	private String account_state;
}
