package org.springsecurity.custom.userDetailsService;

import com.wrok.bean.Role;
import com.wrok.bean.UserInfo;
import com.wrok.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ocean on 2016-09-14.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private RoleDao roleDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> authorList = new ArrayList<>();
        //authorList.add(new SimpleGrantedAuthority("ROLE_admin"));

        List<Role> roles = this.roleDao.queryList(s);
        Iterator<Role> iter = roles.iterator();
        while (iter.hasNext()){
            authorList.add(new SimpleGrantedAuthority(String.format("ROLE_%s",iter.next().getRolename())));
        }
        UserInfo user = this.roleDao.findUser(s);
        User details = new User(s, user.getPassword(),authorList);
        return details;
    }
}
