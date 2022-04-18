package com.yedam.app.member.serviceImpl;

import com.yedam.app.member.service.Member;

public interface MemberMapper {
	
	Member findOne(Member vo);
	int insert(Member vo);
}
