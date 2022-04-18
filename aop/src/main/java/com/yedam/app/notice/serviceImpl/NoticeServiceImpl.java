package com.yedam.app.notice.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.notice.service.NoticeService;
import com.yedam.app.notice.vo.NoticeVO;

@Service
public class NoticeServiceImpl 
        implements NoticeService {

	@Autowired
	private NoticeMapper map;
	
	@Override
	public List<NoticeVO> noticeSelectList(int page) {
		System.out.println("서비스 실행");
		return map.noticeSelectList(page);
	}

	@Override
	public List<NoticeVO> noticeSearchList(String key, String val) {
		return map.noticeSearchList(key, val);
	}

}
