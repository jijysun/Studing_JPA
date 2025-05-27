package HelloJPA.PracticeJPA.repository.region;

import HelloJPA.PracticeJPA.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
