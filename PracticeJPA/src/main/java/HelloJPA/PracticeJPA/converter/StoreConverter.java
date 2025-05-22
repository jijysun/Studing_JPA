package HelloJPA.PracticeJPA.converter;

import HelloJPA.PracticeJPA.domain.Region;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.region.RegionRequestDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;

import java.util.ArrayList;
import java.util.List;

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

    public static StoreResponseDto.ReviewPreviewDto toReviewPreviewDto (Review review) {
        return null;
    }

    public static StoreResponseDto.ReviewPreviewListDto toReviewPreviewListDto (List<Review> reviewList) {
        return null;
    }


}
