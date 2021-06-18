package hello.hellospring.service;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired //생략가능 (1개일때는)
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
 //   public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new jdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
 //   }
}
