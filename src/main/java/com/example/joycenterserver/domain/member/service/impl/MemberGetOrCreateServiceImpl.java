package com.example.joycenterserver.domain.member.service.impl;

import com.example.joycenterserver.domain.member.entity.Member;
import com.example.joycenterserver.domain.member.repository.MemberRepository;
import com.example.joycenterserver.domain.member.service.MemberGetOrCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberGetOrCreateServiceImpl implements MemberGetOrCreateService {

    private final MemberRepository memberRepository;

    @Override
    public Member getOrCreate(String email, String name) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(new Member(email, name)));
    }
}
