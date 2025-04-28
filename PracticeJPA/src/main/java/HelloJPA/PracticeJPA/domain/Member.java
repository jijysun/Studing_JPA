package HelloJPA.PracticeJPA.domain;

import HelloJPA.PracticeJPA.domain.enums.Gender;
import HelloJPA.PracticeJPA.domain.enums.MemberStatus;
import HelloJPA.PracticeJPA.domain.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String specAddress;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private LocalDate inactiveDate;

    private Integer point;

    /*private LocalDate createDate;

    private LocalDate updateDate;*/
    
}
