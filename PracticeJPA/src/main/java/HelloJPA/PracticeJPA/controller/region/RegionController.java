package HelloJPA.PracticeJPA.controller.region;

import HelloJPA.PracticeJPA.common.apiPayload.ApiResponse;
import HelloJPA.PracticeJPA.common.apiPayload.code.status.SuccessStatus;
import HelloJPA.PracticeJPA.converter.RegionConverter;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.dto.region.RegionRequestDto;
import HelloJPA.PracticeJPA.dto.region.RegionResponseDto;
import HelloJPA.PracticeJPA.service.region.RegionCommandServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
@Slf4j
public class RegionController {

    private final RegionCommandServiceImpl regionCommandService;

    @PostMapping("/{regionId}/store")
    public ApiResponse<RegionResponseDto.AddStoreResponseDto> addStore(@PathVariable Long regionId, @RequestBody @Valid RegionRequestDto.AddStoreRequestDto request){
        log.info("/regions/" + regionId + "/store");
        Store store = regionCommandService.addStore(regionId, request);
        return ApiResponse.onSuccess(RegionConverter.toAddStoreResponseDto(store), SuccessStatus._OK);
    }
}
