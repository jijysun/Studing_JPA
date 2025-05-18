package HelloJPA.PracticeJPA.validation.validator;

import HelloJPA.PracticeJPA.common.apiPayload.code.status.ErrorStatus;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.domain.mapping.MemberMission;
import HelloJPA.PracticeJPA.repository.member_mission.MemberMissionRepository;
import HelloJPA.PracticeJPA.repository.mission.MissionRepository;
import HelloJPA.PracticeJPA.validation.annotation.ChallengeMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MissionChallengeValidator implements ConstraintValidator<ChallengeMission, Long> {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ChallengeMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {

        Optional<Mission> mission = missionRepository.findById(missionId);
        Optional<MemberMission> memberMission = memberMissionRepository.findByMissionId(missionId);

        if (mission.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }

        if (memberMission.isPresent()) {
            if (memberMission.get().getStatus() == MissionStatus.CHALLENGING){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString()).addConstraintViolation();
                return false;
            }
        }
        return true;

    }
}
