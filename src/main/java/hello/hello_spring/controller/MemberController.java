package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller  // 이렇게 하면 스프링이 이 컨트롤러의 객체를 가지게 됨
// 스프링 컨테이너에서 스프링 빈이 관리됨 (빈 = 컨트롤러)
public class MemberController {
    private final MemberService memberService;

    // alt + Ins
    @Autowired // 스프링 컨테이너에 있는 컨트롤러와 연결 (의존관계 주입!)
    public MemberController(MemberService memberService) { // 컨테이너에서 관리하기 위해서
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 여기로 들어가면
    public String createForm() {
        return "members/createMemberForm"; // 여기 html 이 뜨도록
    }

    @PostMapping("/members/new")
    // html 에서 post 방식으로 넘어온 것을 받아줌 (데이터 등록)
    // 조회 할때는 Get!
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        // System.out.println("member name is " + member.getName());
        memberService.join(member);
        return "redirect:/"; // home 화면으로 보내기
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> memebers = memberService.findMembers();
        model.addAttribute("members", memebers);
        return "members/memberList";
    }

}
