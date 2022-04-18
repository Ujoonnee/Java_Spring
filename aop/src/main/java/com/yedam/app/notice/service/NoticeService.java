package com.yedam.app.notice.service;

import java.util.List;

import com.yedam.app.notice.vo.NoticeVO;

public interface NoticeService {
	List<NoticeVO> noticeSelectList(int page);
	List<NoticeVO> noticeSearchList(String key, String val);
}
