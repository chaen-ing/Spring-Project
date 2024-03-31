package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // memberService 역할
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        // 기존에는 MemberServiceImpl 클래스에서 직접 구현체를 넣어줬음
        return new MemberServiceImpl(memberRepository());
    }

    // MemberRepository 역할 : MemoryMemberRepository 리턴
    // 만약 추후에 DbMemberRepository로 변경한다면 이부분만 변경해주면됨
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    // OrderService 역할
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        //return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    // DiscountPolicy 역할 : FixDiscountPolicy 리턴
    @Bean
    public DiscountPolicy discountPolicy(){
        // return new FixDiscountPolicy();
        // 정액 -> 정률으로 할인 정책 변경
        return new RateDiscountPolicy();
    }
}
