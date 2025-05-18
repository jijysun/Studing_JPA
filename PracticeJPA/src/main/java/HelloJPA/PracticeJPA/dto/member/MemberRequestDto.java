package HelloJPA.PracticeJPA.dto.member;

import HelloJPA.PracticeJPA.validation.annotation.ChallengeMission;
import HelloJPA.PracticeJPA.validation.annotation.ExistCategories;
import HelloJPA.PracticeJPA.validation.annotation.ExistStores;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MemberRequestDto {


    @Getter
    public static class JoinDto{

        @NotBlank
        String name;

        @NotNull
        Integer gender;

        @NotNull
        Integer birthYear;

        @NotNull
        Integer birthMonth;

        @NotNull
        Integer birthDay;

        @Size(min = 5, max = 12)
        String address;

        @Size(min = 5, max = 12)

        String specAddress;

        @ExistCategories
        List<Long> preferCategory;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddNewReviewRequestDto{

        @NotNull
        @ExistStores
        private Long storeId;

        @NotNull
        private Float score;

        @Size(min = 5, max = 255)
        private String body;

        private List<String> reviewPhoto;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionRequestDto {

        @ChallengeMission
        private Long missionId;
    }
}
