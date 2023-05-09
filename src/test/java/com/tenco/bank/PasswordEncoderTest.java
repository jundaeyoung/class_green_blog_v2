package com.tenco.bank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	// 기능 확인해보기
	public static void main(String[] args) {

		String password = "p1234";

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(password + ": 원래 비밀번호");
		System.out.println(hashedPassword + "암호화된 비밀번호");
		
		// 사용자 요청 값 : 1234
		// DB 기록 되어 있는 값 : $2a$10$1i03s2kleuX3WqNyWTsITOoLzJmv/c/HqQG2bETUytEDRY6oV.cU.
		
		boolean isMatched = passwordEncoder.matches("p1234", hashedPassword);
		System.out.println("비밀번호 일치 여부 : " + isMatched);
	}

}
