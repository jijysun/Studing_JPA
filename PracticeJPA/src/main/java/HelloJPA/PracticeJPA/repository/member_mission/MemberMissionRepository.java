package HelloJPA.PracticeJPA.repository.member_mission;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {



    Integer countMemberMissionByMemberAndStatus(Member member, MissionStatus missionStatus);

    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
}
