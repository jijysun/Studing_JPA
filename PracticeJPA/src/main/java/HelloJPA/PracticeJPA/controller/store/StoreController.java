package HelloJPA.PracticeJPA.controller.store;

import HelloJPA.PracticeJPA.common.apiPayload.ApiResponse;
import HelloJPA.PracticeJPA.common.apiPayload.code.status.SuccessStatus;
import HelloJPA.PracticeJPA.converter.ReviewConverter;
import HelloJPA.PracticeJPA.converter.StoreConverter;
import HelloJPA.PracticeJPA.converter.mission.MissionConverter;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.dto.mission.MissionResponseDTO;
import HelloJPA.PracticeJPA.dto.review.ReviewResponseDto;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;
import HelloJPA.PracticeJPA.service.store.StoreQueryService;
import HelloJPA.PracticeJPA.service.store.StoreService;
import HelloJPA.PracticeJPA.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreService storeService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDto.AddNewMissionResultDto> addMission
            (@PathVariable Long storeId, @RequestBody @Valid StoreRequestDto.AddNewMissionDto request) {

        log.info("StoreController addMission");
        Mission newMission = storeService.addMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toAddNewMissionResultDto(newMission), SuccessStatus._OK);
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰이 필요합니다", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰이 만료되었습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 형식 오류입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, pathVariable 입니다!")
    })
    public ApiResponse<ReviewResponseDto.myReviewListDto> getReviewList(@PathVariable Long storeId, @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDto(reviewList),  SuccessStatus._OK);
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 도전 가능한 미션 목록 조회 API 이며, 페이징을 포함합니다. query String 으로 page 번호를 주시기 바랍니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 조회에 성공했습니다", content = @Content(schema = @Schema(implementation = ReviewResponseDto.myReviewListDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰이 필요해요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰이 만료되었어요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4000", description = "올바르지 않은 가게 번호이에요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "올바르지 않은 페이지 번호 이에요!", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 id, pathVariable 입니다!"),
            @Parameter(name = "page", description = "page 번호, 1부터 시작하는 값 입니다.", in = ParameterIn.QUERY, schema = @Schema(defaultValue = "1"))
    })
    public ApiResponse<MissionResponseDTO.StoreMissionListDTO> getMissionList(@PathVariable Long storeId, @ValidPage @RequestParam(name = "page") Integer page) {
        Page <Mission> storeMissions = storeService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.toStoreMissionListDTO(storeMissions), SuccessStatus._OK);
    }


}
