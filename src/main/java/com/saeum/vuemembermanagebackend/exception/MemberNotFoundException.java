package com.saeum.vuemembermanagebackend.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(Long userKey) {
        super("회원을 찾을 수 없습니다. userKey: " + userKey);
    }

    public MemberNotFoundException(String field, String value) {
        super("회원을 찾을 수 없습니다. " + field + ": " + value);
    }
}
