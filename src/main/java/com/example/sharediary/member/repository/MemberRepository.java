package com.example.sharediary.member.repository;

import com.example.sharediary.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberNameAndPassword(String memberName, String password);

    Member findByMemberName(String memberName);
}
