package HelloJPA.PracticeJPA.converter;

import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.Region;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.mission.MissionResponseDTO;
import HelloJPA.PracticeJPA.dto.region.RegionRequestDto;
import HelloJPA.PracticeJPA.dto.store.StoreResponseDto;
import org.springframework.data.domain.Page;

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


    public static MissionResponseDTO.StoreMissionDTO toMissionDTO (Mission mission){
        return MissionResponseDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadLine(mission.getDeadLine().toString())
                .build();
    }

    public static MissionResponseDTO.StoreMissionListDTO toStoreMissionListDTO (Page <Mission> missions){
        List<MissionResponseDTO.StoreMissionDTO>  dtoList = missions.stream()
                .map(StoreConverter::toMissionDTO).toList();

        return MissionResponseDTO.StoreMissionListDTO.builder()
                .storeMissionList(dtoList)
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .listSize(dtoList.size())
                .totalElements(missions.getTotalElements())
                .totalPage(missions.getTotalPages())
                .build();
    }

}
