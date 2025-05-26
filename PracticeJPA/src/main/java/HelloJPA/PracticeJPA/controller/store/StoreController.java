package HelloJPA.PracticeJPA.controller.store;

import HelloJPA.PracticeJPA.common.apiPayload.ApiResponse;
import HelloJPA.PracticeJPA.common.apiPayload.code.status.SuccessStatus;
import HelloJPA.PracticeJPA.converter.StoreConverter;
import HelloJPA.PracticeJPA.converter.mission.MissionConverter;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;
import HelloJPA.PracticeJPA.service.store.StoreQueryService;
import HelloJPA.PracticeJPA.service.store.StoreService;
import HelloJPA.PracticeJPA.validation.annotation.ExistStores;
import io.swagger.v3.oas.annotations.Operation;
<<<<<<< HEAD
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
=======
>>>>>>> origin/feature/2
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

<<<<<<< HEAD

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
    public ApiResponse<StoreResponseDto.ReviewPreViewListDTO> getReviewList(@ExistStores @PathVariable Long storeId, @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList),  SuccessStatus._OK);
    }


=======
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
>>>>>>> origin/feature/2
}
