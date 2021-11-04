package com.blessing333.stove.infra.config;

/**
*
* 컨트롤러의 엔드포인트 url 상수를 정의한 클래스
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/04
**/
public class UrlConfig {
    // 공통
    public static final String HOME_URL ="/";
    public static final String REDIRECT_URL = "redirect:";

    // 게시글 관련 url
    public static final String POST_URL = "/post";
    public static final String POST_CREATE_FORM_URL = POST_URL + "/post-form";
    public static final String POST_EDIT_URL = POST_URL + "/edit";

    //댓글 관련 url
    public static final String COMMENT_URL = "/comment";

    //카테고리 관련 url
    public static final String CATEGORY_URL = "/category";

    //관리자 관련 url
    public static final String ADMIN_CATEGORY_SETTING_URL = "/admin/category";


}
