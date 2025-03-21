package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Obtional 은 만약에 찾는 값이 NULL 이라면 Obtioanl 로 감싸서 내보내기
    List<Member> findAll(); // 지금까지 저장된거 모두 return
}
