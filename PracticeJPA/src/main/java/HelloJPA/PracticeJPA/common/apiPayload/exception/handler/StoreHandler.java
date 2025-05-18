package HelloJPA.PracticeJPA.common.apiPayload.exception.handler;

import HelloJPA.PracticeJPA.common.apiPayload.code.BaseErrorCode;
import HelloJPA.PracticeJPA.common.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
