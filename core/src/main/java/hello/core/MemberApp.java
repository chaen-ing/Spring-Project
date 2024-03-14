package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // AppConfig 정보를 통해 @Bean들을 스프링컨테이너에서 관리
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);   // 앞에는 메소드 이름, 뒤에는 타입

        Member member = new Member(1L,"memberA",Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName()); // 회원 가입한 멤버
        System.out.println("find Member = " + findMember.getName());  // 회원 조회해서 찾은 멤버

    }
}
