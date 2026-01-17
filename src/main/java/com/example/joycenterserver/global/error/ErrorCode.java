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

    // POST
    NOT_FOUND_POST(404, "해당 게시글을 찾을 수 없습니다."),
    POST_FORBIDDEN(403, "해당 게시글에 대한 권한이 없습니다."),
    INVALID_POST_REQUEST(400, "게시글 요청 데이터가 올바르지 않습니다."),
    INVALID_POST_TITLE(400, "게시글 제목은 1자 이상 100자 이하여야 합니다."),
    INVALID_POST_CONTENT(400, "게시글 내용은 1자 이상 10000자 이하여야 합니다."),

    // ATTACHMENT
    INVALID_ATTACHMENT_FILE(400, "첨부파일이 올바르지 않습니다."),
    INVALID_ATTACHMENT_FILE_TYPE(400, "허용되지 않은 파일 형식입니다."),
    ATTACHMENT_UPLOAD_FAILED(500, "첨부파일 업로드에 실패했습니다."),
    NOT_FOUND_ATTACHMENT(404, "해당 첨부파일을 찾을 수 없습니다."),
    ATTACHMENT_FORBIDDEN(403, "해당 첨부파일에 대한 권한이 없습니다."),

    // COMMON
    INTERNAL_SERVER_ERROR(500, "예기치 못한 서버 에러가 발생했습니다.");

    private final int status;
    private final String message;
}
