package HelloJPA.PracticeJPA.repository.member_mission;

import HelloJPA.PracticeJPA.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findByMissionId(Long missionId);
}
