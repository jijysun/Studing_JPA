package HelloJPA.PracticeJPA.service.store;

import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.repository.store.StoreRepository;
import HelloJPA.PracticeJPA.repository.store.StoreRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final StoreRepositoryImpl storeRepositoryImpl;

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
}
