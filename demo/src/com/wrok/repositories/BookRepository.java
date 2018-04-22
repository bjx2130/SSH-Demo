package com.wrok.repositories;

import com.wrok.bean.Book;
import org.springdata.custom.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mapping.context.AbstractMappingContext;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ocean on 2016-09-29.
 *
 *
 */
//注意此接口命名时不要与dao中的文件名重名
public interface BookRepository extends BaseRepository<Book, Long> {

    //命名查询
    List<Book> findByNameAndSn(String name, String sn);

    //JPQL查询
    @Query("select u from Book u where u.id = ?1")
    Book findJPQL(int id);

    //本地SQL查询
    @Query(value = "SELECT * FROM Book WHERE id = ?1", nativeQuery = true)
    Book findNativeQuery(int id);

    //JPQL属性查询
    @Query("select u from Book u where u.id = :id")
    Book findByParam(@Param("id") int id);
}
