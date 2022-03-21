package com.jpa.jpaup1.repository;


import com.jpa.jpaup1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
