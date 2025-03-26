package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service // 스프링이 서비스로 인식!
@Transactional // jpa 는 모든 데이터 변경이 transaction 안에서 실행됨!
public class MemberService {
    private final MemberRepository memberRepository;

    // @Autowired // 서비스가 리포지토리가 필요하기 때문에 연결!
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        // alt + Ins

    }

    // 회원 가입!
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // ctrl + alt + v 로 한번에 Optional 로 return!
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // null 이 아니라 값이 있으면 ifPresent 작동! (Optional 일때)
        validateDuplicateMember(member);
        // ctrl + shift + alt + t 로 extract method !
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // findByName 은 Optional 을 return 해 줌으로!
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
