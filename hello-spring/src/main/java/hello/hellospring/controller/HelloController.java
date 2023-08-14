package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

@Controller
public class HelloController {

    // 정적 컨텐츠
    // localhost:8080/hello-static.html
    @GetMapping("hello")    // 웹 어플리케이션에서 "hello"가 들어오면 밑에 메소드 실행
    public String hell(Model model){
        model.addAttribute("data","spring!!");
        return "hello";
    }

    // MVC와 템플릿 엔진
    // localhost:80800/hello-mvc?name=어쩌구
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API : String 넘기기
    // localhost:8080/hello-stirng?name=어쩌
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;
    }

    // API : 데이터(객체) 넘기기
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
