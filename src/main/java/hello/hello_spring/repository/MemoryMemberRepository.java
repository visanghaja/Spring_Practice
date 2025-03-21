package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // ctrl + space로 import!
    private static long sequence = 0L; // 0L은 long type의 0


    @Override
    public Member save(Member member) {
        member.setId(++sequence); // name 은 이미 사용자가 정의 했기 때문에 id 만 시스템에서 정해줌
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // stream() !!
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    // Alt + Ins 해서 method 한번에!
}
