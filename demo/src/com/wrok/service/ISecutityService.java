package com.wrok.service;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;

public interface ISecutityService {



	public void add();


	@Secured("ROLE_admin")
	public void injectTest2();



	public void exceptionTest(com.wrok.bean.Book book);
}
