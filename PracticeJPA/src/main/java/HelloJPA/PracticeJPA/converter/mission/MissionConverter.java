package HelloJPA.PracticeJPA.converter.mission;

import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.mission.MissionResponseDTO;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {
    public static Mission toMission (Store targetStore, StoreRequestDto.AddNewMissionDto request) {
        return Mission.builder()
                .reward(request.getReward())
                .deadLine(LocalDateTime.now().plusDays(request.getDeadLine()))
                .missionSpec(request.getMissionSpec())
                .store(targetStore)
                .build();
    }

    public static StoreResponseDto.AddNewMissionResultDto toAddNewMissionResultDto (Mission mission) {
        return StoreResponseDto.AddNewMissionResultDto.builder()
                .reward(mission.getReward())
                .deadLine(mission.getDeadLine())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static MissionResponseDTO.ChallengeMissionResponseDTO toChallengeMissionResponseDTO (Mission mission) {
        return MissionResponseDTO.ChallengeMissionResponseDTO.builder()
                .missionId(mission.getId())
                .deadLine(mission.getDeadLine().toString())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .build();
    }

    public static MissionResponseDTO.ChallengingMissionResponseListDTO toChallengingMissionResponseListDTO (Page<Mission> missions){

        List<MissionResponseDTO.ChallengeMissionResponseDTO> dtoList = missions.stream().map(MissionConverter::toChallengeMissionResponseDTO).toList();

        return MissionResponseDTO.ChallengingMissionResponseListDTO.builder()
                .challengeMissionList(dtoList)
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .listSize(dtoList.size())
                .build();
    }

    public static MissionResponseDTO.CompleteChallengedMissionResponseDTO toCompleteChallengedMissionResponseDTO (Integer count, Mission mission) {
        return MissionResponseDTO.CompleteChallengedMissionResponseDTO.builder()
                .completedMemberMissionCount(count)
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .build();
    }
}
