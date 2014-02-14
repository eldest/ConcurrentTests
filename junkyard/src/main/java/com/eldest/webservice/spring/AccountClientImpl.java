package com.eldest.webservice.spring;

public final class AccountClientImpl {

	private AccountService accountService;

	public void setService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void init() {
		System.out.println(accountService.sayHi("Eldest!"));
	}

}
