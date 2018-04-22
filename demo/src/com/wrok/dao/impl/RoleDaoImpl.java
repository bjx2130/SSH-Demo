package com.wrok.dao.impl;


import com.wrok.bean.Role;
import com.wrok.bean.UserInfo;
import com.wrok.dao.RoleDao;
import com.wrok.dao.UserInfoDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ocean on 2016-09-26.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger log = LogManager.getLogger();
    @PersistenceContext
    private EntityManager emf;
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public void save(Role role) {
        this.emf.persist(role);
    }

    @Override
    public List<Role> queryList(String phone) {
        TypedQuery<Role> pageQuery = this.emf.createQuery("SELECT o FROM UserInfo u JOIN u.role o WHERE u.phone=?",Role.class);
        pageQuery.setParameter(1,phone);
        return pageQuery.getResultList();
    }

    @Override
    public UserInfo findUser(String phone) {
        return userInfoDao.findUser(phone);
    }


}
