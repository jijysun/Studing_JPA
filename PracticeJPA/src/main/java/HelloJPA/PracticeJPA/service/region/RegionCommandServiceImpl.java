package HelloJPA.PracticeJPA.service.region;

import HelloJPA.PracticeJPA.common.apiPayload.code.status.ErrorStatus;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.RegionHandler;
import HelloJPA.PracticeJPA.converter.StoreConverter;
import HelloJPA.PracticeJPA.domain.Region;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.region.RegionRequestDto;
import HelloJPA.PracticeJPA.repository.region.RegionRepository;
import HelloJPA.PracticeJPA.repository.store.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegionCommandServiceImpl implements RegionCommandService {

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Store addStore(Long regionId, RegionRequestDto.AddStoreRequestDto request) {

        Region region = regionRepository.findById(regionId).orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        Store newStore = StoreConverter.toStore(request, region);

        return storeRepository.save(newStore);
    }
}
