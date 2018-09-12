package com.zxy.hrbcu.meeting.interceptor;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The type Mapping exception resolver.
 * Description
 *
 * @author lvzhouyang.
 * @version 1.0
 * @since 2017.02.23
 */
public class MappingExceptionResolver extends SimpleMappingExceptionResolver {
    /**
     * The constant log.
     */
    private final static Logger log = LoggerFactory.getLogger(MappingExceptionResolver.class);
    /**
     * The Exclusion exception list.
     */
    private static List<String> exclusionExceptionList;

    /**
     * 覆盖doResolveException方法，记录错误日志
     *
     * @param request  the request
     * @param response the response
     * @param handler  the handler
     * @param ex       the ex
     * @return the model and view
     * @since 2017.02.23
     */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        String url = this.getUri(request);
        String exceptionName = ex.getClass().getSimpleName();
        if (CollectionUtils.isNotEmpty(exclusionExceptionList) && !exclusionExceptionList.contains(exceptionName)) {
            log.error("url= " + url + " params=" + request.getParameterMap(), ex);
        } else {
            log.warn("url= " + url + " params=" + request.getParameterMap(), ex);
        }
        request.setAttribute("exception",ex);
        return super.doResolveException(request, response, handler, ex);
    }

    private String getUri(HttpServletRequest request) {
        String uri = request.getRequestURI().replaceAll(";.*", "");
        return uri.substring(request.getContextPath().length());
    }
}

