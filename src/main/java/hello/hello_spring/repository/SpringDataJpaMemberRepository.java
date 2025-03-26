package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    // Long 은 구별 PK
    //JpaRepository<Member, Long> 이걸 extend 하면 따로 Config에 Bean 설정 안해도 ok!
    //단순한거는 인터페이스만으로 구현가능!


    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
    // 이렇게 하고 구현 따로 안해도 OK

    //Optional<Member> findByNameAndId(String name, Long id);
    //이렇게나 findByEmail() 같이 메서드 이름만으로 조회기능 제공
}
