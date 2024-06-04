package hellojpa.jpql;

import hellojpa.jpql.Repository.MemberRepository;
import hellojpa.jpql.Repository.TeamRepository;
import hellojpa.jpql.jpql.Member;
import hellojpa.jpql.jpql.MemberDTO;
import hellojpa.jpql.jpql.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


@DataJpaTest
class JpqlApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	TeamRepository teamRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@PersistenceContext
	private EntityManager em;

	@BeforeEach
	public void setUp(){
		Team team = new Team();
		team.setName("A");
		teamRepository.save(team);

		memberRepository.save(new Member("kim",10,team));
		memberRepository.save(new Member("lee",1000,team));
		memberRepository.save(new Member("park",60,team));
		memberRepository.save(new Member("choi",30,team));
	}

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

		List<Member> teamMember = memberRepository.joinTeamMember();

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
