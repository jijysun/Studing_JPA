package HelloJPA.PracticeJPA.converter.member;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.enums.Gender;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.domain.mapping.MemberMission;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.dto.member.MemberResponseDto;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
public class MemberConverter {

    public static MemberResponseDto.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDto.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember (MemberRequestDto.JoinDto request){
        Gender gender=null;

        switch(request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        log.info(request.toString());

        return Member.builder()
                .email(request.getEmail())
                .role(request.getRole())
                .password(request.getPassword())
                .address(request.getAddress())
                .name(request.getName())
                .specAddress(request.getSpecAddress())
                .memberPreferList(new ArrayList<>())
                .gender(gender)
                .build();
    }

    public static MemberResponseDto.ChallengeMissionResponseDto toChallengeMissionResponseDto(Mission mission){
        return MemberResponseDto.ChallengeMissionResponseDto.builder()
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .missionStatus(MissionStatus.CHALLENGING)
                .deadLine(mission.getDeadLine())
                .build();
    }


}