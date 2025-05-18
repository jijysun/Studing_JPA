package HelloJPA.PracticeJPA.controller.member;

import HelloJPA.PracticeJPA.common.apiPayload.ApiResponse;
import HelloJPA.PracticeJPA.common.apiPayload.code.status.SuccessStatus;
import HelloJPA.PracticeJPA.converter.ReviewConverter;
import HelloJPA.PracticeJPA.converter.member.MemberConverter;
import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.dto.member.MemberResponseDto;
import HelloJPA.PracticeJPA.service.member.MemberCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){
        log.info("join");

        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member), SuccessStatus._OK);
    }

    @PostMapping("/{memberId}/reviews")
    public ApiResponse<MemberResponseDto.AddNewReviewResultDTO> addReview
            (@PathVariable Long memberId, @RequestBody @Valid MemberRequestDto.AddNewReviewRequestDto requestDto){
        log.info("addReview");
        Review review = memberCommandService.addReview(memberId, requestDto);
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDto (review), SuccessStatus._OK);
    }
}
