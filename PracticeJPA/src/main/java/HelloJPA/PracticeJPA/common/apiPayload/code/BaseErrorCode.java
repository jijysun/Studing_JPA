package HelloJPA.PracticeJPA.common.apiPayload.code;

public interface BaseErrorCode {

    ErrorReasonDto getReason();

    ErrorReasonDto getReasonHttpStatus();
}
