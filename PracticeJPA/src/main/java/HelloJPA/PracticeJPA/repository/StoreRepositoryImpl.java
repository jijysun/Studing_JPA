package HelloJPA.PracticeJPA.repository;

import HelloJPA.PracticeJPA.domain.QStore;
import HelloJPA.PracticeJPA.domain.Store;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QStore store = QStore.store;

//    public StoreRepositoryImpl(EntityManager em){
//        this.queryFactory = new JPAQueryFactory(em);
//    }

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {

        BooleanBuilder predicate = new BooleanBuilder();

        queryFactory.insert(store)
                .columns(store.name, store.score)
                .values(name, score);

        if (name != null) {
            predicate.and(store.name.eq(name));
        }

        if (score != null) {
            predicate.and(store.score.goe(4.0f));
        }

        return queryFactory
                .selectFrom(store)
                .where()
                .where(predicate)
                .fetch();
    }
}
