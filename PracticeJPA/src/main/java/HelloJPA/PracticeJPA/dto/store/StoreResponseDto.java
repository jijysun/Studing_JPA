package HelloJPA.PracticeJPA.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddNewMissionResultDto {
        private Integer reward;
        private LocalDateTime deadLine;
        private String missionSpec;
    }
}
