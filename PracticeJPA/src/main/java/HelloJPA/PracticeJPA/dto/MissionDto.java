package HelloJPA.PracticeJPA.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class MissionDto {

    private String storeName;
    private Long missionId;
    private String missionName;
    private Integer missionReward;


}
