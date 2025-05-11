package HelloJPA.PracticeJPA.domain;

import HelloJPA.PracticeJPA.domain.common.BaseEntity;
import HelloJPA.PracticeJPA.domain.mapping.MemberAgree;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String text;

    @Column(nullable = false)
    private boolean active;

    @Builder.Default
    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();


//    private LocalDate createDate;

//    private LocalDate updateDate;

}
