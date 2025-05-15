package HelloJPA.PracticeJPA.controller;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.Mission;
import HelloJPA.PracticeJPA.domain.enums.Gender;
import HelloJPA.PracticeJPA.domain.enums.MissionStatus;
import HelloJPA.PracticeJPA.domain.mapping.MemberMission;
import HelloJPA.PracticeJPA.dto.HomeDto;
import HelloJPA.PracticeJPA.dto.MissionDto;
import HelloJPA.PracticeJPA.repository.member.MemberRepository;
import HelloJPA.PracticeJPA.repository.member.MemberRepositoryImpl;
import HelloJPA.PracticeJPA.repository.member_mission.MemberMissionRepository;
import HelloJPA.PracticeJPA.repository.mission.MissionRepository;
import HelloJPA.PracticeJPA.repository.mission.MissionRepositoryImpl;
import HelloJPA.PracticeJPA.repository.review.ReviewRepositoryImpl;
import HelloJPA.PracticeJPA.service.StoreQueryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JpaController {

    private final MemberRepository memberRepository;
    private final MemberRepositoryImpl memberRepositoryImpl;
    private final StoreQueryServiceImpl storeQueryService;
    private final ReviewRepositoryImpl reviewRepositoryImpl;
    private final MissionRepository missionRepository;
    private final MissionRepositoryImpl missionRepositoryImpl;
    private final MemberMissionRepository memberMissionRepository;

    @GetMapping("/save")
    public String save() {
        Member member = Member.builder()
                .email("jijysun@gmail.com")
                .address("test")
                .name("김석현")
                .gender(Gender.MALE)
                .point(123)
                .inactiveDate(LocalDate.now())
                .specAddress("test address")
                .build();

        memberRepository.save(member);

        return "ok";
    }


    // 1, 진행 중, 진행 완료 미션 확인
    // member_mission 에 진행 중, 진행 완료 미션 추가해야 함
    @GetMapping("/saveMission")
    public String saveMission() {

        Member member = memberRepository.findById(1L).get();

        Mission mission1 = missionRepository.findById(1L).get();
        Mission mission2 = missionRepository.findById(2L).get();

        MemberMission challengingMission = MemberMission.builder()
                .mission(mission1)
                .member(member)
                .status(MissionStatus.CHALLENGING)
                .build();

        MemberMission completedMission = MemberMission.builder()
                .mission(mission2)
                .member(member)
                .status(MissionStatus.COMPLETED)
                .build();

        memberMissionRepository.save(challengingMission);
        memberMissionRepository.save(completedMission);
        return "ok";
    }

    // 1
    @GetMapping("/1")
    public String missionOne() {
        List<MissionDto> missionDtos = missionRepositoryImpl.dynamicMissionSelectQuery(1L, MissionStatus.COMPLETED, 10);

        for (MissionDto missionDto : missionDtos) {
            log.info(missionDto.toString());
        }
        return "ok";
    }

    // 2. 리뷰 작성하는 쿼리
    @GetMapping("/2")
    public String missionTwo() {
        reviewRepositoryImpl.dynamicReviewInsertQuery(1L, "test review", 4.4f, 1L);

        return "ok";
    }

    // 3. 홈 화면 쿼리
    @GetMapping("/3")
    public String missionThree() {
        HomeDto home = memberRepositoryImpl.dynamicHomeSelectQuery("서울", 10);
        log.info(home.toString());

        return "ok";
    }


    // 4. 마이 페이지 화면 쿼리
    @GetMapping("/4")
    public String missionFour() {
        log.info(memberRepositoryImpl.dynamicMyPageSelectQuery(1L).toString());
        return "ok";
    }




}
