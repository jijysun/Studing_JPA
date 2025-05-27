package HelloJPA.PracticeJPA.common.apiPayload.code.status;


import HelloJPA.PracticeJPA.common.apiPayload.code.BaseErrorCode;
import HelloJPA.PracticeJPA.common.apiPayload.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    WRONG_PAGE (HttpStatus.BAD_REQUEST, "PAGE4001", "올바르지 않은 페이지 번호 입니다."),
//    END_OF_PAGE (HttpStatus.BAD_REQUEST, "PAGE4002", "마지막 페이지 입니다."),

    MISSION_NOT_FOUND (HttpStatus.BAD_REQUEST, "MISSION4000", "해당 미션이 존재하지 않습니다."),
    MISSION_ALREADY_CHALLENGED (HttpStatus.BAD_REQUEST, "MISSION4001", "이미 도전 중인 미션 입니다"),
    NO_CHALLENGING_MISSIONS (HttpStatus.BAD_REQUEST, "MISSION4002", "도전 중인 미션이 없습니다."),

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러입니다. 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON4000","잘못된 요청입니다."),
    WRONG_INPUT(HttpStatus.BAD_REQUEST, "COMMON4001", "모든 값을 입력해야 합니다."),

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 존재하지 않습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    NICKNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4003", "이미 사용된 닉네임입니다."),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4004", "이미 사용된 이메일입니다."),

    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOODCATEGORY4000", "해당 카테고리가 존재하지 않습니다."),

    REGION_NOT_FOUND(HttpStatus.BAD_REQUEST, "REGION4000", "해당 지역이 존재하지 않습니다."),

    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4000", "해당 가게가 존재하지 않습니다."),

    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트 예외가 발생했습니다."),



    // 추가 예시
    POST_ALREADY_UPDATED (HttpStatus.CONFLICT, "POST4001", "이미 수정된 글입니다."),
    INVALID_FILE_TYPE (HttpStatus.UNSUPPORTED_MEDIA_TYPE, "POST4002", "지원하지 않는 파일 형식입니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}
