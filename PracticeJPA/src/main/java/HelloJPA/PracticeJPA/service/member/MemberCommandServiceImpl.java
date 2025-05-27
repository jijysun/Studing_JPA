package HelloJPA.PracticeJPA.service.member;

import HelloJPA.PracticeJPA.common.apiPayload.code.status.ErrorStatus;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.FoodCategoryHandler;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.UserHandler;
import HelloJPA.PracticeJPA.converter.MemberPreferConverter;
import HelloJPA.PracticeJPA.converter.ReviewConverter;
import HelloJPA.PracticeJPA.converter.member.MemberConverter;
import HelloJPA.PracticeJPA.converter.memberMission.MemberMissionConverter;
import HelloJPA.PracticeJPA.domain.*;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.domain.mapping.MemberMission;
import HelloJPA.PracticeJPA.domain.mapping.MemberPrefer;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.repository.foodCategory.FoodCategoryRepository;
import HelloJPA.PracticeJPA.repository.member.MemberRepository;
import HelloJPA.PracticeJPA.repository.member_mission.MemberMissionRepository;
import HelloJPA.PracticeJPA.repository.mission.MissionRepository;
import HelloJPA.PracticeJPA.repository.review.ReviewRepository;
import HelloJPA.PracticeJPA.repository.store.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Mission challengeMission(Long MemberId, MemberRequestDto.ChallengeMissionRequestDto request) {

        Member requestMember = memberRepository.findById(MemberId).orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission targetMission = missionRepository.findById(request.getMissionId()).orElseThrow(() -> new UserHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission newChallengingMission = MemberMissionConverter.toMemberMission(requestMember, targetMission);
        memberMissionRepository.save(newChallengingMission);

        return targetMission;
    }

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

    @Override
    public Page<Review> getMyReviews(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Page<Review> allByMember = reviewRepository.findAllByMember(member, PageRequest.of(page - 1, 5));
        if (allByMember.isEmpty()){
            throw new UserHandler(ErrorStatus.WRONG_PAGE);
        }
        return allByMember;
    }

    @Override
    public Page<Mission> getChallengingMissions(Long memberId, MissionStatus status, Integer page) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        // 조회된 미션 ID에 대해 미션 조회\
        // select * from mission where id = (select mission_id from member_mission where member_id = ? and status = challenge) + paging
        List<MemberMission> allByMemberAndStatus = memberMissionRepository.findAllByMemberAndStatus(member, status, PageRequest.of(page - 1, 10)).stream().toList();
        Page<Mission> allByMemberMissionList = missionRepository.findAllByMemberMissionList(allByMemberAndStatus, PageRequest.of(page - 1, 10));

        if (allByMemberAndStatus.isEmpty()){
            throw new UserHandler(ErrorStatus.NO_CHALLENGING_MISSIONS);
        }

        return allByMemberMissionList;
    }
}
