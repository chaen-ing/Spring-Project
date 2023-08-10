package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // import해서 바로 assertThat가능

// 클래스 레벨에서 테스트시에 메소드 모두 확인 가능
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메소드가 실행이 끝날때마다 동작하는 콜백 메소드
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    // 저장 테스트
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // 반환이 optional 이므로 get으로 꺼낼 수 있음
        Member result = repository.findById(member.getId()).get();

        /*
        둘이 같으면 참을 반환하는 세가지 방법
        1. System.out.println("result = "+(result ==member));
        2. Assertions 중 org.junit.jupiter.api의 assertEquals 사용 : 두개가 같을시 돌아가고 다르면 에러
           Assertions.assertEquals(member,result);
        3. Assertions 중 org.assertj.core.api의 assertThat 사용
           Assertions.assertThat(member).isEqualTo(result);
         */

        assertThat(member).isEqualTo(result);
    }

    // findByName 테스트
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // spring 2로 찾으면 오류. 다른 객체라고 뜸
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    // findAll 테스트
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        // 3으로 할시 오류
        assertThat(result.size()).isEqualTo(2);
    }
}
