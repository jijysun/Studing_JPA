package HelloJPA.PracticeJPA.repository.member;

import HelloJPA.PracticeJPA.domain.QMember;
import HelloJPA.PracticeJPA.domain.QMission;
import HelloJPA.PracticeJPA.domain.QRegion;
import HelloJPA.PracticeJPA.domain.QStore;
import HelloJPA.PracticeJPA.dto.HomeDto;
import HelloJPA.PracticeJPA.dto.MemberDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;
    private final QStore store = QStore.store;
    private final QMission mission = QMission.mission;
    private final QRegion region = QRegion.region;

    // 3. 홈 화면 쿼리
    public HomeDto dynamicHomeSelectQuery(String regionName, int cursor) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        HomeDto.class,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        mission.deadLine))
                .from(mission)

                .join(store).on(mission.store.id.eq(store.id))
                .join(region).on(store.region.id.eq(region.id))

                .where(
                        region.name.eq(regionName),
                        mission.deadLine.gt(LocalDateTime.now()),
                        mission.id.lt(cursor)
                )

                .orderBy(mission.id.desc())
                .limit(10)
                .fetchOne();
    }

    // 4. 마이페이지 쿼리
    public MemberDto dynamicMyPageSelectQuery(Long memberId) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        MemberDto.class,
                        member.name,
                        member.email,
                        member.phone,
                        member.veritify
                ))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }

}
