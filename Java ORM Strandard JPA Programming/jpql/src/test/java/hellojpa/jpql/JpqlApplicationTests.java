package hellojpa.jpql;

import hellojpa.jpql.Repository.MemberRepository;
import hellojpa.jpql.Repository.TeamRepository;
import hellojpa.jpql.jpql.Member;
import hellojpa.jpql.jpql.MemberDTO;
import hellojpa.jpql.jpql.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Queue;

@SpringBootTest
class JpqlApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	TeamRepository teamRepository;

	@Autowired
	EntityManager em;

	@Test
	void saveTest() {
		Member membera = new Member();
		membera.setUsername("kim");

		Member memberb = new Member();
		memberb.setUsername("kim2");

		memberRepository.save(membera);
		memberRepository.save(memberb);

		List<Member> memberlist = memberRepository.findMember();

		for (Member member : memberlist) {
			System.out.println("member = " + member.getUsername());
		}

		Member findmember = memberlist.get(0);
		System.out.println("findmember = " + findmember.getUsername());
		findmember.setAge(20);
		System.out.println("findmember = " + findmember.getAge());

		memberRepository.save(findmember);
	}

	@Test
	void joinTest(){
		Team team = new Team();
		teamRepository.save(team);

		Member membera = new Member();
		membera.setUsername("kim");
		membera.setTeam(team);
		memberRepository.save(membera);

		Member memberb = new Member();
		memberb.setUsername("kim2");
		memberb.setTeam(team);
		memberRepository.save(memberb);

		List<Member> teamMember = memberRepository.findTeamMember();

		for (Member member : teamMember) {
			System.out.println("member = " + member.getUsername());
		}
	}

	@Test
	void findTest(){
		Member membera = new Member();
		membera.setUsername("kim");
		membera.setAge(10);
		memberRepository.save(membera);

		Member memberb = new Member();
		memberb.setUsername("kim2");
		memberb.setAge(30);
		memberRepository.save(memberb);

		List<MemberDTO> memberNameAge = memberRepository.findMemberNameAge();

		MemberDTO memberDTO = memberNameAge.get(0);
		System.out.println("memberDTO = " + memberDTO.getAge());
		System.out.println("memberDTO = " + memberDTO.getUsername());


	}

}
