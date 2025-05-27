package HelloJPA.PracticeJPA.validation.annotation;

import HelloJPA.PracticeJPA.validation.validator.ValidPageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPageValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {
    String message() default "올바르지 않은 페이지 번호 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
