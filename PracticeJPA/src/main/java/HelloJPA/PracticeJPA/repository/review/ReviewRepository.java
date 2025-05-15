package HelloJPA.PracticeJPA.repository.review;

import HelloJPA.PracticeJPA.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
