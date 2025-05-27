package HelloJPA.PracticeJPA.service.store;

import HelloJPA.PracticeJPA.common.apiPayload.code.status.ErrorStatus;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.StoreHandler;
import HelloJPA.PracticeJPA.converter.mission.MissionConverter;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.store.StoreRequestDto;
import HelloJPA.PracticeJPA.repository.mission.MissionRepository;
import HelloJPA.PracticeJPA.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public Mission addMission(Long storeId, StoreRequestDto.AddNewMissionDto request) {

        Store targetStore = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission (targetStore,request);

        return missionRepository.save(mission);
    }

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Page<Mission> allByStore = missionRepository.findAllByStore(store, PageRequest.of(page - 1, 10));
        if (allByStore.isEmpty()) {
            throw new StoreHandler(ErrorStatus.MISSION_NOT_FOUND);
        }
        return allByStore;
    }
}
