package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // memberService 역할
    public MemberService memberService(){
        // 기존에는 MemberServiceImpl 클래스에서 직접 구현체를 넣어줬음
        return new MemberServiceImpl(memberRepository());
    }

    // MemberRepository 역할 : MemoryMemberRepository 리턴
    // 만약 추후에 DbMemberRepository로 변경한다면 이부분만 변경해주면됨
    private MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    // OrderService 역할
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // DiscountPolicy 역할 : FixDiscountPolicy 리턴
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
