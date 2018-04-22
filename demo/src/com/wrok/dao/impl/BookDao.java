package com.wrok.dao.impl;

import com.wrok.bean.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by ocean on 2016-09-07.
 */
@Repository
public class BookDao {

    private static final Logger log = LogManager.getLogger();

    @PersistenceContext
    private EntityManager emf;

    public void create(Book vo){
        this.emf.persist(vo);

    }

    public void update(Book vo){
        //Book c= this.emf.find(Book.class, vo.getId());
        vo.setSn("ddd-eee-ttt-yyy");
        this.emf.merge(vo);
        this.emf.flush();
    }

    public List<Book> getList(){
    	/**
    	 * TypedQuery 接口继承 Query 接口.
    	 * 
    	 * 1.Query接口应该被用于返回结果类型是未知得和返回多态结果
    	 * 2.TypedQuery 接口用于返回所指定得类型结果
    	 * 
    	 */
        Query query = this.emf.createQuery("select o from Book o");
        //query.executeUpdate();
        return query.getResultList();
    }

}
