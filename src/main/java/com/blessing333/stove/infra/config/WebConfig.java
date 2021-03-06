package com.blessing333.stove.infra.config;

import com.blessing333.stove.infra.interceptor.LoginSessionCheckInterceptor;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
*
* Spring MVC 설정을 위한 Config 클래스
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/10/30
**/
@Configuration
public class WebConfig  implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //스트링 부트에 설정된 기본 정적 파일 패스를 List에 저장
        List<String> staticResourcePath = Arrays.stream(StaticResourceLocation.values())
                .flatMap(StaticResourceLocation::getPatterns)
                .collect(Collectors.toList());
        staticResourcePath.add("/node_modules/**");
        staticResourcePath.add("/assets/**");

        registry.addInterceptor(new LoginSessionCheckInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error") //  에러페이지는 인터셉터 적용 제외
                .excludePathPatterns(staticResourcePath); //static resource에 대해 인터셉터 적용 제외
    }
}
