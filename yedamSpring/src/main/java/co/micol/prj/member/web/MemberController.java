package co.micol.prj.member.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.vo.MemberVO;
import co.micol.prj.util.PasswordEncrypt;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberDao;
	
	@Autowired
	private PasswordEncrypt passwordEncrypt;
	
	@RequestMapping("/memberSelectList.do")
	public String memberSelectList(Model model) {
		model.addAttribute("members", memberDao.memberSelectList());
		return "member/memberSelectList";
	}
	
	@RequestMapping("/memberJoinForm.do")
	public String memberJoinForm() {
		return "member/memberJoinForm";
	}
	
	@PostMapping("/memberJoin.do")
	public String memberJoin(MemberVO vo) {
		vo.setPassword(passwordEncrypt.ecrypt(vo.getPassword()));
		memberDao.memberInsert(vo);
		return "redirect:memberSelectList.do";	// 뷰 리졸브를 거치지 않음
	}
	
	@RequestMapping("/memberLoginForm.do")
	public String memberLoginForm() {
		return "member/memberLoginForm";
	}
	
	@PostMapping("/memberLogin.do")
	public String memberLogin(MemberVO vo, Model model, HttpSession session) {
		vo.setPassword(passwordEncrypt.ecrypt(vo.getPassword()));
		vo = memberDao.memberSelect(vo);
		if (vo != null) {
			session.setAttribute("id", vo.getId());
			session.setAttribute("authority", vo.getAuthority());
			session.setAttribute("name", vo.getName());
			model.addAttribute("message", vo.getName() + "님 환영합니다.");
		} else {
			model.addAttribute("message","아이디 또는 패스워드가 틀렸다.");
		}
	
		return "member/memberLoginMessage";
	}

	@RequestMapping("/memberLogout.do")
	public String memberLogout(HttpSession session, Model model) {
		model.addAttribute("message", session.getAttribute("name") + "님 정상 로그아웃");
		session.invalidate();
		
		return "member/memberMessage";
	}
	
	@RequestMapping(value="/ajaxIdCheck.do", produces="application/text;charset=UTF-8")
	@ResponseBody 
	public String ajaxIdCheck(MemberVO vo) {
		String str = "";
		vo = memberDao.memberSelect(vo);
		
		if (vo != null) {
			str = "yes";
		} else {
			str = "no";
		}

		return str;
	}
	
}
