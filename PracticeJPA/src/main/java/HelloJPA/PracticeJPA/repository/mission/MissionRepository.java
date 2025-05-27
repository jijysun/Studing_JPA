package HelloJPA.PracticeJPA.repository.mission;

import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}
