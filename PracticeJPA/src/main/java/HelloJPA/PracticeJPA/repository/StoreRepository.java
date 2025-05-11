package HelloJPA.PracticeJPA.repository;

import HelloJPA.PracticeJPA.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
