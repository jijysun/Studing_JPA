package HelloJPA.PracticeJPA.service.member;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.dto.MemberRequestDto;

public interface MemberCommandService {

    public Member joinMember (MemberRequestDto.JoinDto request);
}
