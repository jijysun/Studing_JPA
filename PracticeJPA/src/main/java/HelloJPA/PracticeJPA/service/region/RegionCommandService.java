package HelloJPA.PracticeJPA.service.region;

import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.region.RegionRequestDto;

public interface RegionCommandService {
    Store addStore(Long regionId, RegionRequestDto.AddStoreRequestDto request);
}
