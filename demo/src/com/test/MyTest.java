package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

	public static void main(String[] args) throws Exception {
//		Class.forName("com.mysql.jdbc.Driver");//指定连接类型  
//		Connection  conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jpatest?characterEncoding=UTF-8&useSSL=false", "root","000000");//获取连接
//		System.out.println(conn.createStatement());
//
//		Book book=new Book();
//		Book taget=new Book();
//
//		//复制属性使用
//		BeanUtils.copyProperties(book,taget);
//		//输出所有属性
//		ReflectionToStringBuilder.toString(book);
//
//		//格式化日期
//		String[] s = new String[]{"yyyy-MM-dd"};
//		Date d1=DateUtils.truncate(DateUtils.parseDate("2016-05-03",s), Calendar.DAY_OF_MONTH);
//
//		//Hibernate将代理类初始化
//		Hibernate.initialize(book);


//		IntelliJ IDEA提供了一个快捷操作Cmd + Shift + T作为类和测试之间的导航
		//new CrudRepository<Book,Long>();
		//CrudRepository
		//PagingAndSortingRepository
		//JpaRepository
		//PageRequest
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config/application-*.xml");  
	}

}
