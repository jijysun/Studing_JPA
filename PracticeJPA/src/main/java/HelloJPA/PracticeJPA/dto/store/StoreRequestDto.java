package HelloJPA.PracticeJPA.dto.store;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class StoreRequestDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddNewMissionDto {

        @NotNull
        private Integer reward;

        @Min(3)
        @Max(7)
        private Integer deadLine;

        @NotNull
        private String missionSpec;

    }
}
