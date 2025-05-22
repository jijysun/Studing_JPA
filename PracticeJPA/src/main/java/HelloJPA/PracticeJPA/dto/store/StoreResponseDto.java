package HelloJPA.PracticeJPA.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewListDto {
        List <ReviewPreviewListDto> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirstPage;
        Boolean isLastPage;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewDto {
        String ownerNickname;
        Float score;
        String body;
        LocalDate createAt;
    }
}
