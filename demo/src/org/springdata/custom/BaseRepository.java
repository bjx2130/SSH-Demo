package org.springdata.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by ocean on 2016-09-30.
 *
 *  Adding custom behavior to all repositories
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    Page<T> findAll(Iterable<?> iter,Pageable var1);


    Page<T> findAll(Map<String,Object> paramMap,Pageable var1);


}
