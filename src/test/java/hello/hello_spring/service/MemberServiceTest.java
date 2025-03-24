package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // afterEach 로 clear 해주기 위해 MemoryMemberRepository 가져옴

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // test 실행 할때마다 각각 생성 해줌
        // DI 의존
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        // given (어떤 상황이 주어져서)
        Member member = new Member();
        member.setName("spring");

        // when (무언가로 실행되었을때)
        Long saveId = memberService.join(member);

        // then (어떠한 결과가 나오는지)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        // alt + enter 로 static import
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
      //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // memberService.join(member2) 이 로직이 실행될때 IllegalStateException 이 예외가 터져야 함

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 위에 거에서 ctrl + alt + v
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 메세지 받아서 확인하기
        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}