package HelloJPA.PracticeJPA.common.apiPayload.code.status;

import HelloJPA.PracticeJPA.common.apiPayload.code.BaseCode;
import HelloJPA.PracticeJPA.common.apiPayload.code.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK (HttpStatus.OK, "COMMON200", "성공입니다"),
    _LOGIN_SUCCESS(HttpStatus.OK, "COMMON201", "로그인에 성공했습니다."),
    _LOGOUT_SUCCESS(HttpStatus.OK, "COMMON202", "로그아웃에 성공했습니다."),
    _SIGN_IN_SUCCESS(HttpStatus.CREATED, "COMMON203", "회원가입에 성공했습니다."),
    _VALIDATE_SUCCESS (HttpStatus.CONTINUE, "COMMON204", "이메일, 닉네임 검증에 성공했습니다"),
    _IN_PROCESSING(HttpStatus.ACCEPTED, "COMMON205", "이미 정상정으로 받은 요청입니다."),
    _CREATE_SUCCESS(HttpStatus.CREATED, "COMMON206", "게시글 작성에 성공하셨습니다."),

    // Test 처리 성공 응답
    _TEST_EXCEPTION_SUCCESS(HttpStatus.PARTIAL_CONTENT, "TEST200", " 테스트에 성공했습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .httpStatus(status)
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }
}
