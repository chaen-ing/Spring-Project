package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member memeber = memberRepository.findById(memberId);   // 회원정보 조회
        // 할인정책에 멤버 넘김 : 할인 정책 클래스에서 회원 등급에 따른 할인 금액을 계산해서 리턴해줌
        int discountPrice = discountPolicy.discount(memeber, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}