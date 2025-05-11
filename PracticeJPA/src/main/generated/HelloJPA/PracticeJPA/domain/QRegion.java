package HelloJPA.PracticeJPA.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegion is a Querydsl query type for Region
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegion extends EntityPathBase<Region> {

    private static final long serialVersionUID = 645679787L;

    public static final QRegion region = new QRegion("region");

    public final HelloJPA.PracticeJPA.domain.common.QBaseEntity _super = new HelloJPA.PracticeJPA.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Store, QStore> regionStoreList = this.<Store, QStore>createList("regionStoreList", Store.class, QStore.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRegion(String variable) {
        super(Region.class, forVariable(variable));
    }

    public QRegion(Path<? extends Region> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegion(PathMetadata metadata) {
        super(Region.class, metadata);
    }

}

