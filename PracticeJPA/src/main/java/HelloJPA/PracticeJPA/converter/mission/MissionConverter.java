package HelloJPA.PracticeJPA.converter.mission;

import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;

import java.time.LocalDateTime;

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
}
