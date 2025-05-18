package HelloJPA.PracticeJPA.service.store;

import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;

public interface StoreService {
    Mission addMission(Long storeId, StoreRequestDto.AddNewMissionDto request);
}
