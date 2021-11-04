package com.blessing333.stove.infra.exception;

import com.blessing333.stove.modules.user.User;
import com.blessing333.stove.modules.user.UserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
*
* 사용자의 잘못된 요청에 대하여 발생한 예외를 처리해주는 전역 컨트롤러.
 *
 * 잘못된 요청의 예
 *  1. 존재하지 않는 게시글에 조회 요청
 *  2. 존재하지 않는 댓글 조회 요청
 *  3. 존재하지 않는 카테고리 조회 요청
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/05
**/
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExceptionHandler(HttpServletRequest req, IllegalArgumentException e, Model model){
        HttpSession session = req.getSession(false);
        User currentUser;
        if(session != null) {
            currentUser = (User) session.getAttribute(UserConfig.USER_INFO_SESSION_ATTRIBUTE_NAME);
            if(currentUser != null) {
                log.info("'{}' requested '{}'", currentUser.getNickname(), req.getRequestURI());
            }
        }
        else {
            log.info("requested '{}'", req.getRequestURI());
        }
        log.error("bad request", e);
        return "error";
    }

}
