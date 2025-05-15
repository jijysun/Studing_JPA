package HelloJPA.PracticeJPA.repository.store;

import HelloJPA.PracticeJPA.domain.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
