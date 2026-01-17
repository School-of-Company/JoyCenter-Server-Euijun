package com.example.joycenterserver.global.util;

import com.example.joycenterserver.domain.member.entity.Member;
import com.example.joycenterserver.domain.member.repository.MemberRepository;
import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {

    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        Long memberId = getCurrentMemberId();
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_MEMBER));
    }

    public Long getCurrentMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new GlobalException(ErrorCode.UNAUTHORIZED);
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Long) {
            return (Long) principal;
        }

        if (principal instanceof String) {
            try {
                return Long.parseLong((String) principal);
            } catch (NumberFormatException e) {
                throw new GlobalException(ErrorCode.UNAUTHORIZED);
            }
        }

        throw new GlobalException(ErrorCode.UNAUTHORIZED);
    }
}
