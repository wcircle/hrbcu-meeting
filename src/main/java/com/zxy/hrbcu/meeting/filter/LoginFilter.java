package com.zxy.hrbcu.meeting.filter;

import com.zxy.hrbcu.meeting.constant.Constant;
import com.zxy.hrbcu.meeting.domain.TUUser;
import com.zxy.hrbcu.meeting.util.SpringSessionWebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wenxu 2018-2-24 15:45:09
 */
public class LoginFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("启动过滤器");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        logger.info(req.getServletPath());

        int n1 = req.getServletPath().toLowerCase().indexOf("manage");
        int n2 = req.getServletPath().toLowerCase().indexOf("backadmin");
        String token_id = (String) SpringSessionWebUtil.getSessionAttribute("token_id");
        TUUser userVo = (TUUser)SpringSessionWebUtil.getSessionAttribute("userVo");
        if(n1>0){
            if (null == token_id || "".equals(token_id)){
                res.sendRedirect("/index/noLogin.html");
            }else{
                if(userVo.getUserType() == Constant.USER_TYPE_JOIN) {
                    filterChain.doFilter(request, response);
                }else{
                    res.sendRedirect("/index/noLogin.html");
                }
            }
        }else if(n2>0){
            if (null == token_id || "".equals(token_id)){
                res.sendRedirect("/index/noLogin.html");
            }else{
                if(userVo.getUserType() == Constant.USER_TYPE_ADMIN) {
                    filterChain.doFilter(request, response);
                }else{
                    res.sendRedirect("/index/noLogin.html");
                }
            }
        }else{
            filterChain.doFilter(request, response);

        }
        return;
    }
    @Override
    public void destroy() {

    }
}
