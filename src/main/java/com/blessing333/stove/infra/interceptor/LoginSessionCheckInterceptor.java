package com.blessing333.stove.infra.interceptor;

import com.blessing333.stove.modules.user.User;
import com.blessing333.stove.modules.user.UserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.blessing333.stove.modules.user.UserConfig.*;

/**
*
* 로그인된 상황을 가정하기 위해 세션에 사용자 정보를 추가해주는 스프링 인터셉터.
* static resource를 제외한 모든 요청마다 session에 유저 정보가 있는지 확인하고, 없으면 세션에 유저 정보를 저장한다.
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/10/29
**/
@Slf4j
public class LoginSessionCheckInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session == null){
            session = request.getSession(true);
        }
        if(session.getAttribute(USER_INFO_SESSION_ATTRIBUTE_NAME) == null){
            User loginUser = new User(USER_NAME,true);
            session.setAttribute(USER_INFO_SESSION_ATTRIBUTE_NAME, loginUser );
        }
        return true;
    }
}
