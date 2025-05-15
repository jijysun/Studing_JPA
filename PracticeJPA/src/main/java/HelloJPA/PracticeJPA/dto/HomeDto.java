package HelloJPA.PracticeJPA.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class HomeDto {

    private String storeName;
    private Integer reward;
    private String missionSpec;
    private LocalDateTime deadline;
}
