package HelloJPA.PracticeJPA.service.member;

import HelloJPA.PracticeJPA.common.apiPayload.code.status.ErrorStatus;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.FoodCategoryHandler;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.UserHandler;
import HelloJPA.PracticeJPA.converter.MemberPreferConverter;
import HelloJPA.PracticeJPA.converter.ReviewConverter;
import HelloJPA.PracticeJPA.converter.member.MemberConverter;
import HelloJPA.PracticeJPA.domain.FoodCategory;
import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Review;
import HelloJPA.PracticeJPA.domain.Store;
import HelloJPA.PracticeJPA.domain.mapping.MemberPrefer;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.repository.foodCategory.FoodCategoryRepository;
import HelloJPA.PracticeJPA.repository.member.MemberRepository;
import HelloJPA.PracticeJPA.repository.review.ReviewRepository;
import HelloJPA.PracticeJPA.repository.store.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final StoreRepository  storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request) {

        log.info("joinMember");

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public Review addReview(Long memberId, MemberRequestDto.AddNewReviewRequestDto requestDto) {
        log.info("MemberCommandService.addReview");

        Member writeMember = memberRepository.findById(memberId).orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Store targetStore = storeRepository.findById(requestDto.getStoreId()).orElseThrow(() -> new UserHandler(ErrorStatus.STORE_NOT_FOUND));

        Review newReview = ReviewConverter.toReview(writeMember, targetStore, requestDto);

        return reviewRepository.save(newReview);
    }
}
