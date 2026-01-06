package com.example.joycenterserver.domain.member.service;

import com.example.joycenterserver.domain.member.entity.Member;

public interface MemberGetService {

    Member getById(Long memberId);
}
