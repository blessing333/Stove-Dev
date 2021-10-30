package com.blessing333.stove.modules.user;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
*
* 사용자 정보를 나타내는 도메인 클래스
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/10/29
**/

@Getter
@Setter(AccessLevel.PRIVATE)
public class User {
    private String nickname;
    private boolean isAdmin;

    public User(String nickname, boolean isAdmin) {
        this.nickname = nickname;
        this.isAdmin = isAdmin;
    }
}
