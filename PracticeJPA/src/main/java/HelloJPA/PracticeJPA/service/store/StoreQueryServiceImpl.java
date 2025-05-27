package HelloJPA.PracticeJPA.service.store;

import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.repository.review.ReviewRepository;
import HelloJPA.PracticeJPA.repository.store.StoreRepository;
import HelloJPA.PracticeJPA.repository.store.StoreRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final StoreRepositoryImpl storeRepositoryImpl;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {

        List<Store> stores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        stores.forEach(store -> System.out.println("store = " + store));

        return stores;
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store findStore = storeRepository.findById(storeId).get();
        Page<Review> allByStoreId = reviewRepository.findAllByStoreId(findStore, PageRequest.of(page, 10));
        return allByStoreId;
    }
}
