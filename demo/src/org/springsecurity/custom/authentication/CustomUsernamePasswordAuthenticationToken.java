package org.springsecurity.custom.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by ocean on 2016-09-22.
 */
public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String validCode;

    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, String validCode) {
        super(principal, credentials);
        this.validCode = validCode;
    }

    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String validCode) {
        super(principal, credentials, authorities);
        this.validCode = validCode;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
