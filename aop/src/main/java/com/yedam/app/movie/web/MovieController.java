package com.yedam.app.movie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieController {

	@Autowired MovieApi api;
	
	@RequestMapping(value = "/firstMovieNm", produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String firstMovieNm(String targetDt) {
		return api.firstMovie(targetDt);
	}

//	@RequestMapping("/firstMovieNm")
//	@ResponseBody
//	public ResponseEntity<String> firstMovieNm(HttpServletResponse response, String targetDt) {
//		response.setContentType("text/html; charset=UTF-8");
//		System.out.println("targetDt: " + targetDt);
//		String nm = api.firstMovie(targetDt);
//		
//		return new ResponseEntity<String>(nm, null, HttpStatus.OK);
//	}
}
