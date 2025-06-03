package HelloJPA.PracticeJPA.controller.member;

import HelloJPA.PracticeJPA.common.apiPayload.ApiResponse;
import HelloJPA.PracticeJPA.common.apiPayload.code.status.SuccessStatus;
import HelloJPA.PracticeJPA.converter.ReviewConverter;
import HelloJPA.PracticeJPA.converter.member.MemberConverter;
import HelloJPA.PracticeJPA.converter.mission.MissionConverter;
import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.dto.member.MemberResponseDto;
import HelloJPA.PracticeJPA.dto.mission.MissionResponseDTO;
import HelloJPA.PracticeJPA.dto.review.ReviewResponseDto;
import HelloJPA.PracticeJPA.service.member.MemberCommandService;
import HelloJPA.PracticeJPA.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public ApiResponse<MemberResponseDto.LoginResultDTO> login(@RequestBody @Valid MemberRequestDto.LoginRequestDTO request) {
        return ApiResponse.onSuccess(memberCommandService.loginMember(request), SuccessStatus._OK);
    }

    @PostMapping("/join")
    @Operation(summary = "유저 회원가입 API",description = "유저가 회원가입하는 API입니다.")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member), SuccessStatus._OK);
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요", description = "유저가 내 정보를 조회하는 API입니다.", security = { @SecurityRequirement(name = "JWT TOKEN")})
    public ApiResponse<MemberResponseDto.MemberInfoDTO> getMyInfo (HttpServletRequest request){
        return ApiResponse.onSuccess(memberCommandService.getMemberInfo(request), SuccessStatus._OK);
    }


    @PostMapping("/signup")
    public String joinMember (@ModelAttribute("memberJoinDto") MemberRequestDto.JoinDto joinDto, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) return "signup";

        try{
            log.info(joinDto.toString());
            memberCommandService.joinMember(joinDto);
            return "redirect:/login";
        }
        catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }


    @PostMapping("/{memberId}/missions")
    public ApiResponse<MemberResponseDto.ChallengeMissionResponseDto> challengeMission
            (@PathVariable Long memberId, @RequestBody @Valid MemberRequestDto.ChallengeMissionRequestDto request){
        log.info("challengeMission");
        Mission mission = memberCommandService.challengeMission (memberId, request);

        return ApiResponse.onSuccess(MemberConverter.toChallengeMissionResponseDto (mission), SuccessStatus._OK);
    }

    /*@PostMapping("/")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){
        log.info("join");

        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member), SuccessStatus._OK);
    }*/

    @PostMapping("/{memberId}/reviews")
    public ApiResponse<MemberResponseDto.AddNewReviewResultDTO> addReview
            (@PathVariable Long memberId, @RequestBody @Valid MemberRequestDto.AddNewReviewRequestDto requestDto){
        log.info("addReview");
        Review review = memberCommandService.addReview(memberId, requestDto);
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDto (review), SuccessStatus._OK);
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "사용자가 작성한 리뷰 목록 조회 API", description = "사용자가 작성한 리뷰 목록 조회 API 이며, 페이징을 포함합니다. query String 으로 page 번호를 주시기 바랍니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 조회에 성공했습니다", content = @Content(schema = @Schema(implementation = ReviewResponseDto.myReviewListDto.class))),
            // 커스텀 응답 에러로 수정하기.
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "올바르지 않은 페이지 번호 입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰이 필요해요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰이 만료되었어요!", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 id, pathVariable 입니다!"),
            @Parameter(name = "page", description = "page 번호, 1부터 시작하는 값 입니다.", in = ParameterIn.QUERY, schema = @Schema(defaultValue = "1"))
    })
    public ApiResponse<ReviewResponseDto.myReviewListDto> getMyReviews (@PathVariable Long memberId, @ValidPage @RequestParam(name = "page") Integer page){
        Page<Review> myReviews = memberCommandService.getMyReviews(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDto(myReviews), SuccessStatus._OK);
    }

    @GetMapping("/{memberId}/missions/challenging")
    @Operation(summary = "사용자가 진행 중인 미션 목록 조회 API", description = "사용자가 진행 중인 미션 목록 조회 API 이며, 페이징을 포함합니다. query String 으로 page 번호를 주시기 바랍니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 조회에 성공했습니다", content = @Content(schema = @Schema(implementation = ReviewResponseDto.myReviewListDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "올바르지 않은 페이지 번호 입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰이 필요해요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰이 만료되었어요!", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 id, pathVariable 입니다!"),
            @Parameter(name = "page", description = "page 번호, 1부터 시작하는 값 입니다.", in = ParameterIn.QUERY, schema = @Schema(defaultValue = "1"))
    })
    public ApiResponse<MissionResponseDTO.ChallengingMissionResponseListDTO> getChallengingMissions
            (@PathVariable Long memberId, @ValidPage @RequestParam (name = "page") Integer page ){
        Page <Mission> challengingMissions = memberCommandService.getChallengingMissions (memberId, page);
        return ApiResponse.onSuccess(MissionConverter.toChallengingMissionResponseListDTO(challengingMissions), SuccessStatus._OK);
    }

    @PostMapping("/{memberId}/missions/{memberMissionId}/complete")
    @Operation(summary = "진행 중인 미션 진행 완료 요청 API", description = "사용자가 진행 중인 미션을 진행 완료로 바꾸는 API 이며, 페이징을 포함합니다. query String 으로 page 번호를 주시기 바랍니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 조회에 성공했습니다", content = @Content(schema = @Schema(implementation = ReviewResponseDto.myReviewListDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4002", description = "도전 중인 미션이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰이 필요해요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰이 만료되었어요!", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 id, pathVariable 입니다!"),
    })
    public ApiResponse<MissionResponseDTO.CompleteChallengedMissionResponseDTO> completeChallengedMission (@PathVariable Long memberId, @PathVariable Long memberMissionId){
        MissionResponseDTO.CompleteChallengedMissionResponseDTO dto = memberCommandService.completeMission(memberId, memberMissionId);
        return ApiResponse.onSuccess(dto, SuccessStatus._OK);
    }


}
