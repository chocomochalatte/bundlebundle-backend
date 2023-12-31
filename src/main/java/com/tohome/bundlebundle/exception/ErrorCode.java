package com.tohome.bundlebundle.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* Business Exceptions */
    // 400 BAD REQUEST
    INVALID_GROUP_NICKNAME("INVALID_POINT_VALUE", "유효하지 않은 그룹 닉네임입니다.", 400),
    INVALID_ORDER_TYPE("INVALID_ORDER_TYPE", "유효하지 않은 정렬 기준입니다.", 400),
    GROUP_ALREADY_EXIST("GROUP_ALREADY_EXIST", "이미 다른 그룹 장바구니에 속해 있습니다.", 400),
    GROUP_IS_NOT_EMPTY("GROUP_IS_NOT_EMPTY", "그룹 장바구니에 참여하고 있는 다른 사용자가 아직 있습니다.", 400),
    INVALID_INPUT_VALUE("INVALID_INPUT_VALUE", "유효하지 않은 입력값입니다.", 400),
    TYPE_MISMATCH("TYPE_MISMATCH", "입력된 enum값이 유효하지 않습니다.", 400),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "유효하지 않은 HTTP method입니다.", 400),

    // 403 FORBIDDEN
    NOT_A_GROUP_OWNER("NOT_A_GROUP_OWNER", "그룹장이 아니기 때문에 권한이 없습니다.", 403),

    // 404 NOT FOUND
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "존재하지 않는 사용자 ID입니다.", 404),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "존재하지 않는 상품 ID입니다.", 404),
    GROUP_NOT_FOUND("GROUP_NOT_FOUND", "존재하지 않는 그룹 ID입니다.", 404),
    MEMBER_GROUP_NOT_FOUND("MEMBER_GROUP_NOT_FOUND", "아무 그룹에도 속해있지 않습니다.", 404),
    IS_A_GROUP_OWNER("IS_A_GROUP_OWNER", "그룹장이기 때문에 그룹에서 나갈 수 없습니다.", 404),
    URL_NOT_FOUND("URL_NOT_FOUND", "URL을 찾을 수 없습니다.", 404),

    // 500 INTERNAL SERVER ERROR
    DB_QUERY_EXECUTION_ERROR("DB_QUERY_EXECUTION_ERROR", "쿼리가 정상적으로 실행되지 않았습니다.", 500),
    ORDER_CREATE_FAILURE("ORDER_CREATE_FAILURE", "주문 생성하기에 실패하였습니다.", 500),


    /* Spring Basic Exceptions */
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.", 500);

    private final String code;
    private final String message;
    private final int status;

}