package com.tohome.bundlebundle.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Business Exceptions
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "존재하지 않는 사용자 ID입니다.", 404),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "존재하지 않는 상품 ID입니다.", 404),
    GROUP_NOT_FOUND("GROUP_NOT_FOUND", "존재하지 않는 그룹 ID입니다.", 404),
    INVALID_GROUP_NICKNAME("INVALID_POINT_VALUE", "유효하지 않은 그룹 닉네임입니다.", 400),

    // Spring Basic Exceptions
    INVALID_INPUT_VALUE("INVALID_INPUT_VALUE", "유효하지 않은 입력값입니다.", 400),
    TYPE_MISMATCH("TYPE_MISMATCH", "입력된 enum값이 유효하지 않습니다.", 400),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "유효하지 않은 HTTP method입니다.", 400),
    URL_NOT_FOUND("URL_NOT_FOUND", "URL을 찾을 수 없습니다.", 404),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.", 500);

    private final String code;
    private final String message;
    private final int status;

}