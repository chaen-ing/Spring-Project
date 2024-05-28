package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 홈화면으로 보내기
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        // 멤버들 다 가져오는 메소드
        List<Member> members = memberService.findMembers();
        // 멤버 리스트 자체를 model에 담아서 화면에 넘김
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
