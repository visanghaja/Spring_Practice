package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    // Long 은 구별 PK
    //JpaRepository<Member, Long> 이걸 extend 하면 따로 Config에 Bean 설정 안해도 ok!

    @Override
    Optional<Member> findByName(String name);
    // 이렇게 하고 구현 따로 안해도 OK
}
