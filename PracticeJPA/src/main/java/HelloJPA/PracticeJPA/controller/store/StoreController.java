package HelloJPA.PracticeJPA.controller.store;

import HelloJPA.PracticeJPA.common.apiPayload.ApiResponse;
import HelloJPA.PracticeJPA.common.apiPayload.code.status.SuccessStatus;
import HelloJPA.PracticeJPA.converter.mission.MissionConverter;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;
import HelloJPA.PracticeJPA.service.store.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDto.AddNewMissionResultDto>  addMission
            (@PathVariable Long storeId, @RequestBody @Valid StoreRequestDto.AddNewMissionDto request){

        log.info("StoreController addMission");
        Mission newMission = storeService.addMission (storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toAddNewMissionResultDto (newMission), SuccessStatus._OK);
    }
}
