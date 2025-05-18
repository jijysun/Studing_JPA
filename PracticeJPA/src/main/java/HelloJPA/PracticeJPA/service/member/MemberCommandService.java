package HelloJPA.PracticeJPA.service.member;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;

public interface MemberCommandService {

    Member joinMember (MemberRequestDto.JoinDto request);

    Review addReview (Long memberId, MemberRequestDto.AddNewReviewRequestDto requestDto);
}
