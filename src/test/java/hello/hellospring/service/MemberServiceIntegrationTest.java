package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
//통합테스트
@SpringBootTest
@Transactional //db에 반영을 안함 = rollback
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test //ctrl + T : 테스트케이스만들기 단축키 / ctrl + Art + shf + T : 리팩토링
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get(); //ctrl + alrt + V : Optionaal 띄우기
        assertThat(member.getName()).isEqualTo(findMember.getName()); // static만들기: Art + Enter
        //테스트는 정상플로우도 중요하지만 예외플로우가 훨씬 중요하다
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member(); //shf + fn + f6 : 리네임
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}