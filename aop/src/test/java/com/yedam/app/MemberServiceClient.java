package com.yedam.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.member.service.Member;
import com.yedam.app.member.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/*-context.xml")
public class MemberServiceClient {

	@Autowired MemberService memberService;
	
	@Test
	public void insert() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		
		Member vo = Member.builder().id("TestMember")
									.name("TestMember")
									.password(encoder.encode("1111"))
									.authority("User")
									.build();
		memberService.insert(vo);
	}
}
