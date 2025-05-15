package HelloJPA.PracticeJPA.repository.mission;

import HelloJPA.PracticeJPA.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
