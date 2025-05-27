package HelloJPA.PracticeJPA.validation.validator;

import HelloJPA.PracticeJPA.common.apiPayload.code.status.ErrorStatus;
import HelloJPA.PracticeJPA.validation.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPageValidator implements ConstraintValidator<ValidPage, Integer> {
    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {

        if (page == null || page < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.WRONG_PAGE.toString()).addConstraintViolation();
            return false;
        }

        // HOW TO RETURN !?

        return true;
    }
}
