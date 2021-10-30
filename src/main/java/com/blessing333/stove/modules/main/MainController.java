package com.blessing333.stove.modules.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private static final String HOME_URL = "/";

    @GetMapping(HOME_URL)
    public String createMainView(HttpSession session){
        return "index";
    }
}
