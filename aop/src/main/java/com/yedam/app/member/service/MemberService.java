package com.yedam.app.member.service;

public interface MemberService {
	
	Member findOne(Member vo);
	int insert(Member vo);
}
