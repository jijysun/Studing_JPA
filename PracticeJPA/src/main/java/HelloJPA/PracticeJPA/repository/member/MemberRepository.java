package HelloJPA.PracticeJPA.repository.member;

import HelloJPA.PracticeJPA.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
