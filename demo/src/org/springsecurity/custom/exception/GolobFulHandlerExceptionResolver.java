package org.springsecurity.custom.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ocean on 2016-09-28.
 * 用于统一处理Resetful抛出的异常信息
 *
 */

public class GolobFulHandlerExceptionResolver implements HandlerExceptionResolver {
    private static final Logger log = LogManager.getLogger();

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
//        ResponseStatus responseStatus = (ResponseStatus) AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);
//        int statusCode = responseStatus.code().value();
//        String reason = responseStatus.reason();
        log.info("异常处理器---------> "+e.getMessage());
        e.getStackTrace();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("msg",e.getMessage());
        model.put("code",-1);
        return new ModelAndView("", model);
    }

}
