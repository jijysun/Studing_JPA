package HelloJPA.PracticeJPA.common.apiPayload.exception.handler;

import HelloJPA.PracticeJPA.common.apiPayload.code.BaseErrorCode;
import HelloJPA.PracticeJPA.common.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
