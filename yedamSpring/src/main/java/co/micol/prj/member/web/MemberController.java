package co.micol.prj.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.micol.prj.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberDao;
	
	@RequestMapping("/memberList.do")
	public String memberSelectList(Model model) {
		model.addAttribute("members", memberDao.memberSelectList());
		return "member/memberSelectList";
	}
}
