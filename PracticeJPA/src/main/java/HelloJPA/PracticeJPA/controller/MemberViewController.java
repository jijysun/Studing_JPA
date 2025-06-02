package HelloJPA.PracticeJPA.controller;

import HelloJPA.PracticeJPA.dto.member.MemberRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


public class MemberViewController {

    @GetMapping ("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberJoinDto", new MemberRequestDto.JoinDto());
        return "signup";
    }

    @GetMapping("/home")
    public String home (){
        return "home";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
