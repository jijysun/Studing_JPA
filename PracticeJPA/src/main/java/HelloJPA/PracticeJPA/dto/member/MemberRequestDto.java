package HelloJPA.PracticeJPA.dto.member;

import HelloJPA.PracticeJPA.domain.enums.Role;
import HelloJPA.PracticeJPA.validation.annotation.ChallengeMission;
import HelloJPA.PracticeJPA.validation.annotation.ExistCategories;
import HelloJPA.PracticeJPA.validation.annotation.ExistStores;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

public class MemberRequestDto {


    @Getter
    @Setter
    @ToString
    public static class JoinDto{

        // RequestDTO 에는 private 키워드를 사용하지 않는 이유는?

        @Email
        String email;

        @NotBlank
        String password;

        @NotNull
        Role role;

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
