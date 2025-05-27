package HelloJPA.PracticeJPA.domain.mapping;


import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.domain.common.BaseEntity;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    public void completeMission (){
        this.status = MissionStatus.COMPLETED;
    }

}