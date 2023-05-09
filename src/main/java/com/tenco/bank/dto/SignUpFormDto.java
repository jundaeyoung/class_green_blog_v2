package com.tenco.bank.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SignUpFormDto {
	private String username;
	private String password;
	private String fullname;
	// file 은 name file 속성과 일치 시켜야 함
	private MultipartFile file;
	// file에서 여러 개를 받고 싶다면
	// private MultipartFile[] files;
	
	// 원래 이미지명 
	private String originFileName;
	// 실제 업로드 된 이미지 명
	private String uploadFileName;
	
	
}
