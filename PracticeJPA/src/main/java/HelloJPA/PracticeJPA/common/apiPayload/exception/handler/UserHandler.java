package HelloJPA.PracticeJPA.common.apiPayload.exception.handler;


import HelloJPA.PracticeJPA.common.apiPayload.code.BaseErrorCode;
import HelloJPA.PracticeJPA.common.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
