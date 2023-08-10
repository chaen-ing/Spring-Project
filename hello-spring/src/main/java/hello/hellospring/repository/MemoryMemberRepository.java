package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();   // save를 위해 Map 사용
    private static long sequence = 0L;  // 0,1,2... 같은 키 값을 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // sequence하나 올려서 아이디 세팅
        store.put(member.getId(), member);  // id, member를 store에 저장
        return member;
    }

    // store에서 Id로 get하면됨
    // null이 반환될 가능성이 있으므로 Optional.ofNullable로 감싸서 반환시 Null도 감싸서 반환 가능
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // lambda 사용
    // 루프 돌면서 member.getName이 파라미터로 넘어온 name과 같을시 filter링하고 반환
    // findAny는 하나라도 찾을시 반환
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // store에 있는 values 즉, 멤버들을 반환해서 ArrayList로 반환
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
        // 비워주는 메소드
    }
}
