package com.tenco.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.bank.dto.SignInFormDto;
import com.tenco.bank.dto.SignUpFormDto;
import com.tenco.bank.handler.exception.CustomRestfullException;
import com.tenco.bank.repository.interfaces.UserRepository;
import com.tenco.bank.repository.model.User;

@Service // IoC 대상
public class UserService {

	@Autowired // DI 처리 ( 객체 생성 시 의존 주입 처리 )
	private UserRepository userRepository;

	@Autowired //DI 처리 - WebMvcConfig에서 Ioc 처리 함
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	// 메서드 호출이 시작될 때 트랜잭션에 시작
	// 메서드 종료시 트랜잭션 종료 (commit)
	public void createUser(SignUpFormDto signUpFormDto) {

		String rawPwd = signUpFormDto.getPassword();
		String hashPwd = passwordEncoder.encode(rawPwd);
		signUpFormDto.setPassword(hashPwd); // 객체 상태 변경
		
		int result = userRepository.insert(signUpFormDto);
		if (result != 1) {
			throw new CustomRestfullException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 로그인 서비스
	 * 
	 * @param signInFormDto
	 * @return userEntity 응답
	 */
	@Transactional
	public User signIn(SignInFormDto signInFormDto) {
		User userEntity = userRepository.findByUsername(signInFormDto);

//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(password);
//		System.out.println(password + ": 원래 비밀번호");
//		System.out.println(hashedPassword + "암호화된 비밀번호");
		
		// 사용자 요청 값 : 1234
		// DB 기록 되어 있는 값 : $2a$10$1i03s2kleuX3WqNyWTsITOoLzJmv/c/HqQG2bETUytEDRY6oV.cU.
		System.out.println(userEntity);
		boolean isMatched = passwordEncoder.matches(signInFormDto.getPassword(), userEntity.getPassword());
		System.out.println("비밀번호 일치 여부 : " + isMatched);
//		User userEntity = userRepository.findByUsernameAndPassword(signInFormDto);
		System.out.println(isMatched);
		if (isMatched == false) {
			throw new CustomRestfullException("아이디 혹은 비밀번호가 틀렸습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return userEntity;
	}
}
