package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // localhost로 접속시 바로 호출됨
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
