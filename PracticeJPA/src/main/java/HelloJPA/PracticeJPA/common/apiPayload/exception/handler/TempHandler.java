package HelloJPA.PracticeJPA.common.apiPayload.exception.handler;


import HelloJPA.PracticeJPA.common.apiPayload.code.BaseErrorCode;
import HelloJPA.PracticeJPA.common.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
