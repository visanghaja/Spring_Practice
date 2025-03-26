package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 이렇게 해서 test를 spring 과 연결 (스프링 컨테이너와 함께 실행)
@Transactional // test를 실행전에 실행되어서 테스트가 시작되면 insertQuery 했다가 끝나면 RollBack 해줌
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    // test 를 가장 마지막 부분 즉, injection 받고 끝이기 때문에 그냥 이렇게 연결

    @Test
    // @Commit // 이렇게 하면 DB 에 남음
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