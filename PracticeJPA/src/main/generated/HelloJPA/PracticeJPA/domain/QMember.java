package HelloJPA.PracticeJPA.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 502705745L;

    public static final QMember member = new QMember("member1");

    public final HelloJPA.PracticeJPA.domain.common.QBaseEntity _super = new HelloJPA.PracticeJPA.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<HelloJPA.PracticeJPA.domain.enums.Gender> gender = createEnum("gender", HelloJPA.PracticeJPA.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> inactiveDate = createDate("inactiveDate", java.time.LocalDate.class);

    public final ListPath<HelloJPA.PracticeJPA.domain.mapping.MemberAgree, HelloJPA.PracticeJPA.domain.mapping.QMemberAgree> memberAgreeList = this.<HelloJPA.PracticeJPA.domain.mapping.MemberAgree, HelloJPA.PracticeJPA.domain.mapping.QMemberAgree>createList("memberAgreeList", HelloJPA.PracticeJPA.domain.mapping.MemberAgree.class, HelloJPA.PracticeJPA.domain.mapping.QMemberAgree.class, PathInits.DIRECT2);

    public final ListPath<HelloJPA.PracticeJPA.domain.mapping.MemberMission, HelloJPA.PracticeJPA.domain.mapping.QMemberMission> memberMissionList = this.<HelloJPA.PracticeJPA.domain.mapping.MemberMission, HelloJPA.PracticeJPA.domain.mapping.QMemberMission>createList("memberMissionList", HelloJPA.PracticeJPA.domain.mapping.MemberMission.class, HelloJPA.PracticeJPA.domain.mapping.QMemberMission.class, PathInits.DIRECT2);

    public final ListPath<HelloJPA.PracticeJPA.domain.mapping.MemberPrefer, HelloJPA.PracticeJPA.domain.mapping.QMemberPrefer> memberPreferList = this.<HelloJPA.PracticeJPA.domain.mapping.MemberPrefer, HelloJPA.PracticeJPA.domain.mapping.QMemberPrefer>createList("memberPreferList", HelloJPA.PracticeJPA.domain.mapping.MemberPrefer.class, HelloJPA.PracticeJPA.domain.mapping.QMemberPrefer.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<HelloJPA.PracticeJPA.domain.enums.SocialType> socialType = createEnum("socialType", HelloJPA.PracticeJPA.domain.enums.SocialType.class);

    public final StringPath specAddress = createString("specAddress");

    public final EnumPath<HelloJPA.PracticeJPA.domain.enums.MemberStatus> status = createEnum("status", HelloJPA.PracticeJPA.domain.enums.MemberStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

