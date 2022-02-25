package com.jpa.jpaup1.service;

import com.jpa.jpaup1.domain.Member;
import com.jpa.jpaup1.dto.member.MemberResponse;
import com.jpa.jpaup1.dto.member.UpdateMemberRequest;
import com.jpa.jpaup1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * @param member
     * @return
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); //같은 이름으로 동시에 가입할 경우가 있어서 db에서 name을 unique로 한다
        memberRepository.save(member);

//        testPersist(member);

        return member.getId();
    }

// 넘겨도 영속성은 지속되어서 set한것만으로도 업데이트 알아서 되네
//    @Transactional
//    public void testPersist(Member member) {
//        log.warn("memberId:" + member.getId());
//
//        member.setName("changed");
//
//        Member one = memberRepository.findOne(member.getId());
//
//        log.warn(one.getName());
//
//    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("already exist");
        }

    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public MemberResponse findOne(Long memberId) {

        Member one = memberRepository.findOne(memberId);

        System.out.println("=======================================");

        Member two = memberRepository.findOne(memberId);

        MemberResponse build = MemberResponse.builder().name(one.getName()).build();

        return build;
    }

    @Transactional
    public void update(Long id, UpdateMemberRequest request) {

        Member member = memberRepository.findOne(id);

        member.setName(request.getName());

    }
}
