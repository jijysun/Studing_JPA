package HelloJPA.PracticeJPA.common.apiPayload.exception;

import HelloJPA.PracticeJPA.common.apiPayload.code.BaseErrorCode;
import HelloJPA.PracticeJPA.common.apiPayload.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode errorCode;

    public ErrorReasonDto getReason (){
        return this.errorCode.getReason();
    }

    public ErrorReasonDto getReasonHttpStatus (){
        return this.errorCode.getReasonHttpStatus();
    }

}
