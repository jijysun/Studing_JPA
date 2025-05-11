package HelloJPA.PracticeJPA.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTerms is a Querydsl query type for Terms
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTerms extends EntityPathBase<Terms> {

    private static final long serialVersionUID = -1778429200L;

    public static final QTerms terms = new QTerms("terms");

    public final HelloJPA.PracticeJPA.domain.common.QBaseEntity _super = new HelloJPA.PracticeJPA.domain.common.QBaseEntity(this);

    public final BooleanPath active = createBoolean("active");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<HelloJPA.PracticeJPA.domain.mapping.MemberAgree, HelloJPA.PracticeJPA.domain.mapping.QMemberAgree> memberAgreeList = this.<HelloJPA.PracticeJPA.domain.mapping.MemberAgree, HelloJPA.PracticeJPA.domain.mapping.QMemberAgree>createList("memberAgreeList", HelloJPA.PracticeJPA.domain.mapping.MemberAgree.class, HelloJPA.PracticeJPA.domain.mapping.QMemberAgree.class, PathInits.DIRECT2);

    public final StringPath text = createString("text");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTerms(String variable) {
        super(Terms.class, forVariable(variable));
    }

    public QTerms(Path<? extends Terms> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTerms(PathMetadata metadata) {
        super(Terms.class, metadata);
    }

}

