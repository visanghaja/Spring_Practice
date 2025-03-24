package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest { // 클래스에서 테스트 돌리면 전체 기능 테스트 가능!
    MemoryMemberRepository repository = new MemoryMemberRepository();
    // ctrl + b 눌러서 이동 !!!!

    @AfterEach // 모든 메소드가 한번씩 실행 할때마다 실행
    public void afterEach() {
        // 메소드 끝날때마다 clear 해주도록 (콜백 메소드)
        repository.clearStore();

    }

    @Test // JUnit
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); // 뒤에 get 은 OPTIONAL 에서 값을 꺼내기 위해서
        assertThat(member).isEqualTo(result);
        // ALT + ENTER 로 스태틱 !

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

        // shift + F6 을 사용해서 member1 을 다 member2 로 바꿀 수 있음
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);


    }
}
