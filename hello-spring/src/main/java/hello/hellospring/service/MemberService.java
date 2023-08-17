package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

// @service : 스프링 컨테이너에 등록해줌

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // test 클래스와 같은 db를 사용하기 위해서 변경
    // dependency injection : DI
    // @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){

        long start = System.currentTimeMillis();

        try{
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("join = "+timeMs+"ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        // member에 null이 아닌 값이 있으면 동작
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("findMembers = "+timeMs+"ms");
        }
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
