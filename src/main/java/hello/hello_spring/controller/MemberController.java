package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller  // 이렇게 하면 스프링이 이 컨트롤러의 객체를 가지게 됨
// 스프링 컨테이너에서 스프링 빈이 관리됨 (빈 = 컨트롤러)
public class MemberController {
    private final MemberService memberService;

    // alt + Ins
    @Autowired // 스프링 컨테이너에 있는 컨트롤러와 연결 (의존관계 주입!)
    public MemberController(MemberService memberService) { // 컨테이너에서 관리하기 위해서
        this.memberService = memberService;
    }
}
