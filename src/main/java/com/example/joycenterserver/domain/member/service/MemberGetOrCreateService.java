package com.example.joycenterserver.domain.member.service;

import com.example.joycenterserver.domain.member.entity.Member;

public interface MemberGetOrCreateService {

    Member getOrCreate(String email, String name);
}
