package HelloJPA.PracticeJPA.repository.review;


import HelloJPA.PracticeJPA.domain.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ReviewRepositoryImpl  {
    private final EntityManager em;

    // 2. 리뷰 작성하는 쿼리 .
    @Transactional
    public void dynamicReviewInsertQuery(Long memberId, String body, Float score, Long storeId) {
        Member memberInfo = em.getReference(Member.class, memberId);
        Store storeInfo = em.getReference(Store.class, storeId);

        Review review = Review.builder()
                .body(body)
                .score(score)
                .member(memberInfo)
                .store(storeInfo)
                .build();

        em.persist(review);
    }
}
