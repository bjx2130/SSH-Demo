package org.springsecurity.custom.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 用于处理登录失败
 * Created by ocean on 2016-12-13.
 */
public class CustomAuthenticationFailureHandler  implements AuthenticationFailureHandler {
    private static final Logger log = LogManager.getLogger();
    private String defaultFailureUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(this.defaultFailureUrl == null) {
            log.debug("没有设置默认的跳转地址，response输出错误信息！");
            response.setContentType("text/html;charset=UTF-8");
            OutputStream out = response.getOutputStream();
            out.write(exception.getMessage().getBytes("UTF-8"));
            out.close();
        } else {
            request.setAttribute("SPRING_SECURITY_LAST_EXCEPTION",exception);
            request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
        }
    }

    public String getDefaultFailureUrl() {
        return defaultFailureUrl;
    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
    }
}
