package com.test;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wrok.bean.Book;
import com.wrok.bean.Role;
import com.wrok.bean.UserInfo;
import com.wrok.dao.RoleDao;
import com.wrok.repositories.BookRepository;
import com.wrok.service.ISecutityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application-*.xml"})
@ActiveProfiles("JPA-MODEL")//用于指定环境
public class TestCase {
	private static final Logger log = LogManager.getLogger();

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BookRepository bookDao;

	@Autowired
	private ISecutityService secutityService;
	
	
	@PersistenceContext
    private EntityManager emf;
	
    @Test
    @Transactional
    public void initDate(){
    	log.info("刷新模式："+this.emf.getFlushMode());
    	UserInfo u = new UserInfo();
    	u.setAge(22);
    	u.setIdCard("610113198901072130");
    	u.setPhone("13991104190");
    	u.setSex("男");
    	u.setRemark("测试数据");
    	
    	Role r = new Role();
    	r.setRolename("manager");
    	r.setDescribes("拥有所有得power");
    	u.setRole(r);
    	this.emf.persist(u);
    	
    }
	
	
	
	
	@Test
	@Transactional
	public void test() throws Exception {
		log.info("SpringJUnit4   测试---------------------");
		UserInfo user = roleDao.findUser("13991104190");
		log.info("---------********************----->"+user);
//		log.info("---------********************----->"+user.getRole().getRolename());
	}

	@Test
	@Transactional
	public void book(){
		//bookDao.findByNameAndSn("cc","dd");
//		bookDao.findAll(new PageRequest(0,3)).forEach((a)->{
//			log.info("----------->>>{}",a.getName());
//		});

		Page<Book> all = bookDao.findAll(new HashMap(), new PageRequest(1, 3));
        log.info("总条数：{}   总页数：{}    pageNum：{}    pageSize：{}",all.getTotalElements(),all.getTotalPages(),all.getNumber(),all.getSize());
        all.getContent().stream().forEach((c)->{
            log.info("----------->>>{}",c.getName());
        });

	}

	@Test
	@Transactional
	public void bookquery(){
		//bookDao.findByNameAndSn("cc","dd");
		Book b = bookDao.findByParam(1);
		log.info("------------------>"+b.getName());
	}

	@Test
	public void rollBack(){

		Book b = new Book();
		b.setName("异常回滚");
		b.setCreateTime(new Date());
		b.setSn("sdfs-sdf-s-dfsd-f");
		secutityService.exceptionTest(b);
	}

}
