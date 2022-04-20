package com.yedam.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MovieVO {

	String movieCd;
	String movieNm;
	String rank;
	
}
