package com.wrok.dao;



import com.wrok.bean.Role;
import com.wrok.bean.UserInfo;

import java.util.List;

/**
 * Created by ocean on 2016-09-26.
 */
public interface RoleDao {

    public void save(Role role);

    public List<Role> queryList(String phone);


    public UserInfo findUser(String phone);

}
