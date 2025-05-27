package HelloJPA.PracticeJPA.repository.foodCategory;

import HelloJPA.PracticeJPA.domain.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory,Long> {
}
