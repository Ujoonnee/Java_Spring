package com.yedam.app.movie.web;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yedam.app.notice.vo.NoticeVO;

@Service
public class MovieApi {

	ObjectMapper om = new ObjectMapper();
	
	public String firstMovie(String targetDt) {
		
		String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		targetDt = (targetDt == null)? yesterday : targetDt;
		
		String boxUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=" + targetDt;
		
		String movieName = "";
		try {
			JsonNode json = om.readTree(new URL(boxUrl));
			JsonNode mv = json.get("boxOfficeResult")
					.get("dailyBoxOfficeList")
					.get(0);
			movieName = mv.get("movieNm").asText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return movieName;
	}
	
	
	public void readTreeTest2() throws MalformedURLException, IOException {
		String infoUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=20212725"; 
		
		JsonNode json = om.readTree(new URL(infoUrl));
//		System.out.println(json);
		String director = json.get("movieInfoResult")
						  .get("movieInfo")
						  .get("directors")
						  .get(0)
						  .get("peopleNm").asText();
			
		System.out.println(director);
	}
	
	public void readTest() {
		String str = "{\"id\":0,\"title\":\"notice title\",\"content\":\"notice content\",\"wdate\":null,\"hit\":0}";
		try {
			NoticeVO vo = om.readValue(str, NoticeVO.class);
			assertEquals("notice title", vo.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void writeTest() {
		NoticeVO vo = NoticeVO.builder().title("notice title").content("notice content").build();

		try {
			String str = om.writeValueAsString(vo);
//			System.out.println(str);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
}
