package HelloJPA.PracticeJPA.repository.mission;

import HelloJPA.PracticeJPA.domain.QMember;
import HelloJPA.PracticeJPA.domain.QMission;
import HelloJPA.PracticeJPA.domain.QStore;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.domain.mapping.QMemberMission;
import HelloJPA.PracticeJPA.dto.MissionDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    // 1. 내가 진행 중, 진행 완료한 미션 모아서 보는 쿼리 (페이징 포함)
    public List<MissionDto> dynamicMissionSelectQuery(Long memberId, MissionStatus status, int cursor) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionDto.class,
                        store.name,
                        mission.id,
                        mission.missionSpec,
                        mission.reward))
                .from(member)
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .join(store).on(mission.store.id.eq(store.id))

                .where(
                        member.id.eq(memberId),
                        memberMission.status.eq(status),
                        mission.id.lt(cursor)
                )

                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }
}