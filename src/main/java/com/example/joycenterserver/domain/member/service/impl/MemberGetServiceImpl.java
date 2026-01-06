package com.example.joycenterserver.domain.member.service.impl;

import com.example.joycenterserver.domain.member.entity.Member;
import com.example.joycenterserver.domain.member.repository.MemberRepository;
import com.example.joycenterserver.domain.member.service.MemberGetService;
import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberGetServiceImpl implements MemberGetService {

    private final MemberRepository memberRepository;

    @Override
    public Member getById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_MEMBER));
    }
}
