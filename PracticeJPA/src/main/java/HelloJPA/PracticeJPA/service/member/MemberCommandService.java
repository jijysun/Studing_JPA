package HelloJPA.PracticeJPA.service.member;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import org.springframework.data.domain.Page;

public interface MemberCommandService {

    Mission challengeMission (Long MemberId, MemberRequestDto.ChallengeMissionRequestDto request);

    Member joinMember (MemberRequestDto.JoinDto request);

    Review addReview (Long memberId, MemberRequestDto.AddNewReviewRequestDto requestDto);

    Page<Review> getMyReviews (Long memberId, Integer page);

    Page<Mission> getChallengingMissions (Long memberId, MissionStatus status,  Integer page);
}
