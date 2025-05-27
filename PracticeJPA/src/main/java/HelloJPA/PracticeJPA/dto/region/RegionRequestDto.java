package HelloJPA.PracticeJPA.dto.region;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class RegionRequestDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddStoreRequestDto {

        @NotNull
        private String name;

        @Size(min = 5, max = 50)
        private String address;

        @NotNull
        private Float score;
    }
}
