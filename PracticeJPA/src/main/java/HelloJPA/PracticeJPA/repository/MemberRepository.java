package HelloJPA.PracticeJPA.repository;

import HelloJPA.PracticeJPA.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
