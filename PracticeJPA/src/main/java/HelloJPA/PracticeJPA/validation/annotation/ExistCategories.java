package HelloJPA.PracticeJPA.validation.annotation;

import HelloJPA.PracticeJPA.validation.validator.CategoriesExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoriesExistValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategories {

    String message() default "Category does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
