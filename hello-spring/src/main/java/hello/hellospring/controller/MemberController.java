package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @controller : 컨트롤러 객체 생성하고 스프링 컨테이너에 넣고 관리
@Controller
public class MemberController {

    /*
    1. 객체를 여러개 생성할 필요가 없으므로 아래처럼 선언하는건 X
    private final MemberService memberService = new MemberService();

    2. 스프링 컨테이너에 두고 쓰는 방법
    */
    private final MemberService memberService;

    //Autowired : 스프링에 있는 멤버서비스에 멤버서비스를 연결시켜줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}
