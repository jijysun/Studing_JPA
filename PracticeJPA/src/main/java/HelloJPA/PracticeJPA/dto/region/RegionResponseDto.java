package HelloJPA.PracticeJPA.dto.region;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class RegionResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddStoreResponseDto{
        Long storeId;
        String storeName;
        LocalDateTime createdAt;
    }
}
