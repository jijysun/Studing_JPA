package HelloJPA.PracticeJPA.service.member;

import HelloJPA.PracticeJPA.common.apiPayload.code.status.ErrorStatus;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.FoodCategoryHandler;
import HelloJPA.PracticeJPA.converter.MemberPreferConverter;
import HelloJPA.PracticeJPA.converter.member.MemberConverter;
import HelloJPA.PracticeJPA.domain.FoodCategory;
import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.mapping.MemberPrefer;
import HelloJPA.PracticeJPA.dto.MemberRequestDto;
import HelloJPA.PracticeJPA.repository.foodCategory.FoodCategoryRepository;
import HelloJPA.PracticeJPA.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request) {

        Member member = MemberConverter.toMember(request);

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
            return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
        }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);;
        memberPreferList.forEach(prefer -> {
            prefer.setMember(member);
        });

        return memberRepository.save(member);
    }
}
