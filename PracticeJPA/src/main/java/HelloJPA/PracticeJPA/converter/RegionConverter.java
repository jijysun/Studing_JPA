package HelloJPA.PracticeJPA.converter;

import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.region.RegionResponseDto;

public class RegionConverter {
    public static RegionResponseDto.AddStoreResponseDto toAddStoreResponseDto (Store store) {
        return RegionResponseDto.AddStoreResponseDto.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
