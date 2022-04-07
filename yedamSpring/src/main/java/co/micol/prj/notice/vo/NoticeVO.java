package co.micol.prj.notice.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO {
	private int id;
	private String title;
	private String content;
	private Date wdate;
	private int hit;
	private String fileName;
	private String uuidFile;
}
