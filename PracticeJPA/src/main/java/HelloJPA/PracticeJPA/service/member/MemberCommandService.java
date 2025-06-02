package HelloJPA.PracticeJPA.service.member;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.dto.member.MemberResponseDto;
import HelloJPA.PracticeJPA.dto.mission.MissionResponseDTO;
import org.springframework.data.domain.Page;

public interface MemberCommandService {

    Mission challengeMission (Long MemberId, MemberRequestDto.ChallengeMissionRequestDto request);

    Member joinMember (MemberRequestDto.JoinDto request);

    Review addReview (Long memberId, MemberRequestDto.AddNewReviewRequestDto requestDto);

    Page<Review> getMyReviews (Long memberId, Integer page);

    Page<Mission> getChallengingMissions (Long memberId,   Integer page);

    MissionResponseDTO.CompleteChallengedMissionResponseDTO completeMission(Long memberId, Long memberMissionId );

    MemberResponseDto.LoginResultDTO loginMember(MemberRequestDto.LoginRequestDTO request);
}
