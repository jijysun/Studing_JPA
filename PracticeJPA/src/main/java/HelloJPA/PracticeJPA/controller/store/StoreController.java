package HelloJPA.PracticeJPA.controller.store;

import HelloJPA.PracticeJPA.common.apiPayload.ApiResponse;
import HelloJPA.PracticeJPA.common.apiPayload.code.status.SuccessStatus;
import HelloJPA.PracticeJPA.converter.mission.MissionConverter;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;
import HelloJPA.PracticeJPA.service.store.StoreService;
import HelloJPA.PracticeJPA.validation.annotation.ExistStores;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping("/{storeId}/reviews")
    @Operation (summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주시기 바랍니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공!"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰이 필요해요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰이 만료되었어요!", )
    })
    public ApiResponse<StoreResponseDto.ReviewPreviewListDto> getReviewList (@ExistStores @PathVariable Long storeId){


        return null;
    }
}
