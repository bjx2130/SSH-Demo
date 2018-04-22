package org.springsecurity.custom.authenProvider;

import com.wrok.bean.UserInfo;
import com.wrok.dao.UserInfoDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by ocean on 2016-09-13.
 */
@Component
public class CustomAuthenProvider implements AuthenticationProvider {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserInfoDao userInfoDao;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        authentication.getPrincipal();
        //username
        System.out.println("user name: "+authentication.getName());
        //password
        System.out.println("password: "+authentication.getCredentials());
        System.out.println("getPrincipal: "+authentication.getPrincipal());
        System.out.println("getAuthorities: "+authentication.getAuthorities());
        System.out.println("getDetails: "+authentication.getDetails());

        UserInfo user = userInfoDao.findUser(authentication.getName());
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在:"+authentication.getName());
        }
        if (!authentication.getCredentials().equals(user.getPassword())){
            throw new UsernameNotFoundException("密码错误:"+authentication.getName());
        }


        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authentication.getName());
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                userDetails, authentication.getCredentials(),userDetails.getAuthorities());
        result.setDetails(userDetails);
        return result;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
