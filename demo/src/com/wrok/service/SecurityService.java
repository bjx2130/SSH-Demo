package com.wrok.service;

import com.wrok.dao.impl.BookDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import javax.annotation.security.RolesAllowed;
import java.awt.print.Book;

@Service
public class SecurityService implements ISecutityService{
	private static final Logger log = LogManager.getLogger();
	


	@Autowired
	private BookDao bookDao;
	
	
	@Transactional
	@RolesAllowed("admin")
	public void add(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("service 层:  》》》》》》》》》"+ bookDao);

	}


	public void injectTest2(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Hello 2  》》》》》》》》》" + authentication.getPrincipal());
	}

	@Transactional
	public void exceptionTest(com.wrok.bean.Book book) {
		bookDao.create(book);

	}
}
