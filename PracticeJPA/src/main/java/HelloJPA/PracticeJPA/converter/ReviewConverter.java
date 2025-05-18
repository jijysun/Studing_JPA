package HelloJPA.PracticeJPA.converter;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.dto.member.MemberResponseDto;

import java.util.ArrayList;

public class ReviewConverter {

    public static MemberResponseDto.AddNewReviewResultDTO toAddReviewResultDto(Review review){
        return MemberResponseDto.AddNewReviewResultDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(Member writeMember, Store store, MemberRequestDto.AddNewReviewRequestDto requestDto){
        return Review.builder()
                .body(requestDto.getBody())
                .score(requestDto.getScore())
                .store(store)
                .reviewImageList(new ArrayList<>())
                .member(writeMember)
                .build();
    }


}
