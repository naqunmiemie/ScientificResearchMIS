package org.yjn.mis.interceptor;


import org.yjn.mis.utils.CookieUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String t = CookieUtils.getCookie("t",request);
        if (t == null){
            response.sendRedirect("/admin/login");
            return false;
        }
        if (t.equals("admin")) {
            return true;
        }else {
            response.sendRedirect("/admin/login");
            return false;
        }
    }
}