package com.tenco.bank.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.bank.repository.model.Account;

@Mapper // MyBatis 연결 처리
public interface AccountRepository {

	public int insert(Account account);
	public int updateById(Account account);
	public int delteById(int id);

	public Account findById(int id);
	public List<Account> findAll(); // 관리자용

	public List<Account> findByUserId(Integer userId);
	
	// 코드 추가 - 계좌 번호로 찾는 기능 추가
	public Account findByNumber(String number);
}
