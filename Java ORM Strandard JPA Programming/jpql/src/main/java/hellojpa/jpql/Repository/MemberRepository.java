package hellojpa.jpql.Repository;

import hellojpa.jpql.jpql.Member;
import hellojpa.jpql.jpql.MemberDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.username like 'kim%'")
    List<Member> findMember();

    @Query("select m from Member m where m.username like 'kim%'")
    Member findSingleMember();

    @Query("select m from Member m, Team t where m.username = t.name")
    List<Member> joinTeamMember();

    @Query("select new hellojpa.jpql.jpql.MemberDTO(m.username, m.age) from Member m")
    List<MemberDTO> findMemberNameAge();

    @Query("select " +
            "case when m. age <= 10 then '학생요금' " +
            "when m.age >= 60 then '경로요금' " +
            "else '일반요금'" +
            "end from Member m")
    List<Member> caseMember();


}

