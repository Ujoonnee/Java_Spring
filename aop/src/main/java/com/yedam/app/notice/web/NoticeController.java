package com.yedam.app.notice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.notice.service.NoticeService;
import com.yedam.app.notice.vo.NoticeVO;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/noticeList.do")
	public String noticeList(@RequestParam(required = false, defaultValue = "1") int page, Model model) {
		model.addAttribute("notices", noticeService.noticeSelectList(page));
		return "notice/noticeList";
	}
	
	@PostMapping("/noticeSearch.do")
	@ResponseBody
	public List<NoticeVO> noticeSearch(@RequestParam String key, @RequestParam String val) {
		return noticeService.noticeSearchList(key,val);
	}
}
