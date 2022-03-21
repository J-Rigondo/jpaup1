package com.jpa.jpaup1.service;

import com.jpa.jpaup1.domain.Member;
import com.jpa.jpaup1.repository.ItemRepository;
import com.jpa.jpaup1.repository.MemberJpaRepository;
import com.jpa.jpaup1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService2Impl implements MemberService2 {
    private final MemberRepository memberRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final ItemRepository itemRepository;


    @Override
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //같은 이름으로 동시에 가입할 경우가 있어서 db에서 name을 unique로 한다
        memberRepository.save(member);

//        testPersist(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("already exist");
        }

    }

    public Member findOne(Long id) {

        Optional<Member> byId = memberJpaRepository.findById(id);

        if(byId.get().getId() == 1L)
            throw new IllegalStateException("not match id");

        return byId.get();
    }
}
