package HelloJPA.PracticeJPA.validation.annotation;

import HelloJPA.PracticeJPA.validation.validator.StoresExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoresExistValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStores {
    String message() default "Store not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
