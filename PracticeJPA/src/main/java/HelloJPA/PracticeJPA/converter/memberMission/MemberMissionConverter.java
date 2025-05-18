package HelloJPA.PracticeJPA.converter.memberMission;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.domain.mapping.MemberMission;

public class MemberMissionConverter {
    public static MemberMission toMemberMission(Member requestMember, Mission targetMission) {
        return MemberMission.builder()
                .member(requestMember)
                .mission(targetMission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
