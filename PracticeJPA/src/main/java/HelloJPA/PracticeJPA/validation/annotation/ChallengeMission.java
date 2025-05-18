package HelloJPA.PracticeJPA.validation.annotation;

import HelloJPA.PracticeJPA.validation.validator.MissionChallengeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionChallengeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChallengeMission {
    String message() default "This Mission already challenged.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
