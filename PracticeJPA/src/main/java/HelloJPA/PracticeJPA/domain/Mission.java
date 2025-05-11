package HelloJPA.PracticeJPA.domain;

import HelloJPA.PracticeJPA.domain.common.BaseEntity;
import HelloJPA.PracticeJPA.domain.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false)
    private Integer reward;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDate deadLine;

    @Column(nullable = false)
    private String missionSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

//    private LocalDate createDate;

//    private LocalDate updateDate;



}
