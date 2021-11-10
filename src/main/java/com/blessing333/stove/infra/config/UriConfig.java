package com.blessing333.stove.infra.config;

/**
*
* 컨트롤러의 엔드포인트 url 상수를 정의한 클래스
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/04
**/
public class UriConfig {
    // 공통
    public static final String HOME_URI ="/";
    public static final String REDIRECT_URI = "redirect:";

    // 게시글 관련 url
    public static final String POST_URI = "/post";
    public static final String POST_CREATE_FORM_URI = POST_URI + "/post-form";
    public static final String POST_EDIT_URI = POST_URI + "/edit";

    //댓글 관련 url
    public static final String COMMENT_URI = "/comment";

    //카테고리 관련 url
    public static final String CATEGORY_URI = "/category";

    //관리자 관련 url
    public static final String ADMIN_CATEGORY_SETTING_URI = "/admin/category";


}
