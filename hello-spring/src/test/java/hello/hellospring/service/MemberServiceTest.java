package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // MemberSeryice와 MemberServiceTest가다른 리포지토리 객체를 생성한것이 됨
    // 만약 Map이 static이 아니라면 다른 DB가 되는셈

    // 메소드 실행 전 동작
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 메소드가 실행이 끝날때마다 동작하는 콜백 메소드
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {   // 한글써도 상관없음
        // given : 주어진 조건
        Member member = new Member();
        member.setName("spring");

        // when : 검증할 것
        Long saveId = memberService.join(member);

        // then : 검증하기
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given : 이름이 중복되는 2명의 회원
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        /* 방법 1 : try, catch
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        // 방법 2 : 람다 로직 실행시 앞의 예외가 터져야함. 다른 예외를 넣는다면 예외가 터지지 않으므로 실패
        // 메세지를 리턴함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}