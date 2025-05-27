package HelloJPA.PracticeJPA.repository.review;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, Pageable pageable);

    Page<Review> findAllByMember(Member member, Pageable pageable);
}
