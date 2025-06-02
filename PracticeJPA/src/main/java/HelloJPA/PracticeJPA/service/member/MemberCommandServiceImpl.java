package HelloJPA.PracticeJPA.service.member;

import HelloJPA.PracticeJPA.common.apiPayload.code.status.ErrorStatus;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.FoodCategoryHandler;
import HelloJPA.PracticeJPA.common.apiPayload.exception.handler.UserHandler;
import HelloJPA.PracticeJPA.config.security.jwt.JwtTokenProvider;
import HelloJPA.PracticeJPA.converter.MemberPreferConverter;
import HelloJPA.PracticeJPA.converter.ReviewConverter;
import HelloJPA.PracticeJPA.converter.member.MemberConverter;
import HelloJPA.PracticeJPA.converter.memberMission.MemberMissionConverter;
import HelloJPA.PracticeJPA.converter.mission.MissionConverter;
import HelloJPA.PracticeJPA.domain.*;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.domain.mapping.MemberMission;
import HelloJPA.PracticeJPA.domain.mapping.MemberPrefer;
import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import HelloJPA.PracticeJPA.dto.member.MemberResponseDto;
import HelloJPA.PracticeJPA.dto.mission.MissionResponseDTO;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

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

        try{
            Member newMember = MemberConverter.toMember(request);

            log.info(newMember.toString());

            newMember.encodePassword(passwordEncoder.encode(newMember.getPassword()));

            List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                    .map(category ->
                    {return foodCategoryRepository.findById(category)
                            .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                    }).collect(Collectors.toList());

            List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

            memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

            return memberRepository.save(newMember);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
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
    public Page<Mission> getChallengingMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        PageRequest pageable = PageRequest.of(page - 1, 10); // 해당 변수는 페이징 시 단 한 번만!!!

        Page<MemberMission> allByMemberAndStatus = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, pageable);

        if (allByMemberAndStatus.isEmpty()){
            throw new UserHandler(ErrorStatus.WRONG_PAGE);
        }

        // Id 먼저 추출
        List<Long> missionIds = allByMemberAndStatus.stream().map(memberMission -> memberMission.getMission().getId()).collect(Collectors.toList());

        List<Mission> allById = missionRepository.findAllById(missionIds);

        // 찾은 여러 Mission  페이징
        return new PageImpl<>(allById, pageable,allByMemberAndStatus.getTotalElements() );
    }

    @Override
    @Transactional
    public MissionResponseDTO.CompleteChallengedMissionResponseDTO completeMission(Long memberId, Long memberMissionId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(memberMissionId).orElseThrow(() -> new UserHandler(ErrorStatus.MISSION_NOT_FOUND));

        // ver1
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId).orElseThrow(() -> new UserHandler(ErrorStatus.MISSION_NOT_FOUND));
        memberMission.completeMission();

        // ver2
        MemberMission newMemberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.COMPLETED)
                .build();
        memberMissionRepository.save(newMemberMission);

        Integer completedMission = memberMissionRepository.countMemberMissionByMemberAndStatus(member, MissionStatus.COMPLETED);

        return MissionConverter.toCompleteChallengedMissionResponseDTO(completedMission, mission);
    }

    @Override
    public MemberResponseDto.LoginResultDTO loginMember(MemberRequestDto.LoginRequestDTO request) {

        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new UserHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getEmail(), null, Collections.singleton(() -> member.getRole().name()));

        String accessToken = jwtTokenProvider.generateToken(authentication);
        return MemberConverter.toLoginResultDTO(member, accessToken);
    }


}
