package co.micol.prj.notice.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {
	private int id;
	private String title;
	private String content;
	private Date wdate;
	private int hit;
}
