package com.blessing333.stove.infra.interceptor;

import com.blessing333.stove.modules.user.User;
import com.blessing333.stove.modules.user.UserConfig;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
*
* 세션에
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/10/29
**/
public class LoginSessionCheckInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            User loginUser = new User(UserConfig.USER_NAME,true);
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", loginUser );
        }
        return true;
    }
}
