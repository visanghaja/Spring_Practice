package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    // JPA는 EntityManager로 모든것이 동작

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist는 영구적인 저장이라는 뜻
        // 이렇게 하면 INSERT QUERY도 해주고 setId 도 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        // find는 조회할 타입이랑 식별자 pk 값 넣어주면 조회가 됨
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();

        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        // return em.createQuery("select m from Member m", Member.class).getResultList();
        // alt + enter
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        // table 에 쿼리를 날리는 것이 아니라 Entity 에다가 쿼리를 날림
        return result;
    }
}
