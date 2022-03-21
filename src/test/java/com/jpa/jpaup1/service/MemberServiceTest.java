package com.jpa.jpaup1.service;

import com.jpa.jpaup1.domain.Member;
import com.jpa.jpaup1.domain.Order;
import com.jpa.jpaup1.repository.MemberRepository;
import com.jpa.jpaup1.repository.OrderJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@SpringBootTest //스프링부트 올려서 테스트
@Transactional //롤백
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("park");

        //when
        Long saveId = memberService.join(member);


        //then

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

    @Test
    public void 작동테스트() throws Exception {
        //given
        Optional<Order> byId = orderJpaRepository.findById(1L);
        Order o = byId.get();
        System.out.println("o = " + o);
        //when

        //then
    }

    @Test
    public void oneToMany쿼리() throws Exception {
        //given
        List<Order> order = orderJpaRepository.findByMember_Name("userA");

        for (Order order1 : order) {
            String name = order1.getMember().getName();
            System.out.println("name = " + name);
        }
        //when

        //then
    }
    
    @Test
    public void oneTomany() throws Exception {
        //given
        List<Order> orders = orderJpaRepository.findByOrderDateLessThanEqual(LocalDateTime.now());

        for (Order order : orders) {
            String name = order.getMember().getName();
            System.out.println("name = " + name);
        }
        
        //when
        
        //then
    }
}