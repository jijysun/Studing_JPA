package HelloJPA.PracticeJPA.converter;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.dto.member.MemberResponseDto;
import HelloJPA.PracticeJPA.dto.review.ReviewResponseDto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

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

    public static ReviewResponseDto.myReviewDto toMyReviewDto(Review review){
        return ReviewResponseDto.myReviewDto.builder()
                .score(review.getScore())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDto.myReviewListDto toMyReviewListDto(Page<Review> reviews){
        List<ReviewResponseDto.myReviewDto> dtoList = reviews.stream()
                .map(ReviewConverter::toMyReviewDto).toList();

        return ReviewResponseDto.myReviewListDto.builder()
                .reviewList(dtoList)
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .totalElements(reviews.getTotalElements())
                .totalPage(reviews.getTotalPages())
                .listSize(reviews.getSize())
                .build();
    }

}
