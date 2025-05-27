package HelloJPA.PracticeJPA.service.store;

import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;
import org.springframework.data.domain.Page;

public interface StoreService {
    Mission addMission(Long storeId, StoreRequestDto.AddNewMissionDto request);

    Page<Mission> getMissionList (Long storeId, Integer page);
}
