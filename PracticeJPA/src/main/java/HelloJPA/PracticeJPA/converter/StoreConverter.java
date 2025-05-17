package HelloJPA.PracticeJPA.converter;

import HelloJPA.PracticeJPA.domain.Region;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.region.RegionRequestDto;
import HelloJPA.PracticeJPA.dto.region.RegionResponseDto;

import java.util.ArrayList;

public class StoreConverter {

    public static Store toStore (RegionRequestDto.AddStoreRequestDto req, Region region) {
        return Store.builder()
                .name(req.getName())
                .address(req.getAddress())
                .score(req.getScore())
                .region(region)
                .storeReviewList(new ArrayList<>())
                .build();
    }
}
