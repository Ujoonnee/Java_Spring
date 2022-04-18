package com.yedam.app.member.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.member.service.Member;
import com.yedam.app.member.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService, UserDetailsService {

	@Autowired MemberMapper map;
	
	@Override
	public Member findOne(Member vo) {
		return map.findOne(vo);
	}
	
	@Override
	@Transactional
	public int insert(Member vo) {
		map.insert(vo);
		map.insert(vo);
		return 0;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member vo = new Member();
		vo.setId(username);
		Member result = map.findOne(vo);
		
		if (result == null) throw new UsernameNotFoundException("no user");
		
		return result;
	}

}
