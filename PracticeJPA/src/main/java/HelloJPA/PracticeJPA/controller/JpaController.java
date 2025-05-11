package HelloJPA.PracticeJPA.controller;

import HelloJPA.PracticeJPA.domain.Member;
import HelloJPA.PracticeJPA.domain.enums.Gender;
import HelloJPA.PracticeJPA.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class JpaController {

    private final MemberRepository memberRepository;

    @GetMapping("/save")
    public String save() {
        Member member = Member.builder()
                .email("jijysun@gmail.com")
                .address("서울 양천구")
                .name("김석현")
                .gender(Gender.MALE)
                .point(123)
                .inactiveDate(LocalDate.now())
                .specAddress("test address")
                .build();

        memberRepository.save(member);

        return "ok";
    }
}
