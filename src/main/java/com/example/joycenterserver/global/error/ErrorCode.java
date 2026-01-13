package com.example.joycenterserver.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // AUTH
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    UNAUTHORIZED(401, "인증에 실패했습니다."),
    INVALID_OAUTH_CODE(400, "유효하지 않은 OAuth 코드입니다."),
    UNVERIFIED_EMAIL(400, "이메일 인증이 필요합니다."),
    UNSUPPORTED_OAUTH_PROVIDER(400, "지원하지 않는 OAuth Provider입니다."),

    // MEMBER
    NOT_FOUND_MEMBER(404, "해당 회원을 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(409, "이미 등록된 이메일입니다."),

    // COMMON
    INTERNAL_SERVER_ERROR(500, "예기치 못한 서버 에러가 발생했습니다.");

    private final int status;
    private final String message;
}
