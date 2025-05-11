package HelloJPA.PracticeJPA.repository;

import HelloJPA.PracticeJPA.domain.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
