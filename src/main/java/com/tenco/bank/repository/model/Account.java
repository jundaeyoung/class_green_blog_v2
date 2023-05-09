package com.tenco.bank.repository.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import org.springframework.http.HttpStatus;

import com.tenco.bank.handler.exception.CustomRestfullException;

import lombok.Data;

@Data
public class Account {
	private Integer id;
	private String number;
	private String password;
	private Long balance;
	private Integer userId;
	private Timestamp createdAt;

	public void withdraw(Long amount) {
		this.balance -= amount;
	}

	public void deposit(Long amount) {
		this.balance += amount;
	}

	// 패스워드 체크
	public void checkPassword(String password) {
		if (!this.password.equals(password)) {
			throw new CustomRestfullException("계좌 비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST);
		}
	}

	// 잔액 여부 확인
	public void checkBalacne(Long amount) {
		if (this.balance < amount) {
			throw new CustomRestfullException("출금잔액이 부족합니다.", HttpStatus.BAD_REQUEST);
		}
	}

	// 계좌 소유자 확인
	public void checkOwner(Integer principalId) {
		if (this.userId != principalId) {
			throw new CustomRestfullException("계좌 소유자가 아닙니다.", HttpStatus.FORBIDDEN);
		}
	}

	public String formatMoney(Long money) {
		DecimalFormat df = new DecimalFormat("###,###");
		String formatNumber = df.format(money);
		return formatNumber + "원";
	}

}