package com.wrok.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wrok.bean.Book;
import com.wrok.repositories.BookRepository;

/**
 * http://localhost:8080/demo/jpa/books
 * Created by ocean on 2016-09-30.
 */
@Controller
@RequestMapping("/jpa")
public class Less12JpaPageable {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/books", method= RequestMethod.GET,produces= MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public Page<Book> getEntryByParams(@PageableDefault(value = 15, sort = { "id" },
            direction = Sort.Direction.DESC)  Pageable pageable) {
        /**
         * page，第几页，从0开始，默认为第0页
         * size，每一页的大小，默认为20
         * sort，排序相关的信息，以property,property(,ASC|DESC)的方式组织
         */

    	Page<Book> page = this.bookRepository.findAll(new HashMap(),pageable);
    	List<Book> list = page.getContent();
    	for(int i=0;i<list.size();i++){
    		Book b = list.get(i);
    		b.setName("处理过的书名+"+b.getName());
    	}
    	
    	
    	return page;
        //return bookRepository.findAll(pageable);
    }
}
