package HelloJPA.PracticeJPA.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;


}