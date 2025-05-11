package HelloJPA.PracticeJPA.domain.mapping;



import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Terms;
import HelloJPA.PracticeJPA.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberAgree extends BaseEntity {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;

}