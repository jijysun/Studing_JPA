package HelloJPA.PracticeJPA.converter;

import HelloJPA.PracticeJPA.domain.Region;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.region.RegionRequestDto;
<<<<<<< HEAD
import HelloJPA.PracticeJPA.dto.region.RegionResponseDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
=======
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;

import java.util.ArrayList;
import java.util.List;
>>>>>>> origin/feature/2

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

<<<<<<< HEAD
    public static StoreResponseDto.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName()) // Object Graph Search
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDto.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponseDto.ReviewPreViewDTO> dtoList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).toList();
        return StoreResponseDto.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(dtoList.size())
                .reviewList(dtoList)
                .build();
    }
=======
    public static StoreResponseDto.ReviewPreviewDto toReviewPreviewDto (Review review) {
        return null;
    }

    public static StoreResponseDto.ReviewPreviewListDto toReviewPreviewListDto (List<Review> reviewList) {
        return null;
    }


>>>>>>> origin/feature/2
}
