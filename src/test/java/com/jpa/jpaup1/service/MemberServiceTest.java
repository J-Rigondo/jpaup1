package com.jpa.jpaup1.service;

import com.jpa.jpaup1.domain.Member;
import com.jpa.jpaup1.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest //스프링부트 올려서 테스트
@Transactional //롤백
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("park");

        //when
        Long saveId = memberService.join(member);


        //then
        Assert.assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("park1");

        Member member2 = new Member();
        member2.setName("park1");


        //when
        memberService.join(member1);
        //memberService.join(member2);

        //then
        //Assert.fail("exception");
    }

    @Test
    public void 회원찾기() {
        List<Member> members = memberService.findMembers();
        for (Member member : members) {
            System.out.println("member = " + member);
        }

        Member member1 = new Member();
        member1.setName("park1");
        memberService.join(member1);


        memberService.findOne(member1.getId());
    }
}