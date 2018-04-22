package org.springdata.custom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ocean on 2016-09-30.
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private static final Logger log = LogManager.getLogger();

    private final EntityManager entityManager;
    private JpaEntityInformation<T, ?> entityInformation;


    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }



    @Override
    public Page<T> findAll(Iterable<?> iter, Pageable var1) {
        log.info("调用全局方法-----> findAll(Iterable<?> iter, Pageable var1)");

        return null;
    }

    @Override
    public Page<T> findAll(Map<String, Object> paramMap, Pageable var1) {
        log.info("调用全局方法-----> findAll(Map<String, Object> paramMap, Pageable var1)");

        String JPQLCount  = "select count(o) from "+ entityInformation.getEntityName() +" o where 1=1";
        TypedQuery<Long> countQuery =  this.entityManager.createQuery(JPQLCount,Long.class);
//        JpqlParamHelper.setParams(countQuery,map);

        String JPQLPage = "select o from "+ entityInformation.getEntityName() +" o where 1=1";
        TypedQuery<T> pageQuery = this.entityManager.createQuery(JPQLPage,entityInformation.getJavaType());
//        JpqlParamHelper.setParams(pageQuery,map);
        pageQuery.setFirstResult(var1.getPageNumber());
        pageQuery.setMaxResults(var1.getPageSize());

        return new PageImpl(pageQuery.getResultList(),var1,countQuery.getSingleResult());
    }


}
