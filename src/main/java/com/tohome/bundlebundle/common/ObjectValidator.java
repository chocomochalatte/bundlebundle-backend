package com.tohome.bundlebundle.common;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;

public class ObjectValidator {

    public static void validateQueryResult(Integer result) {
        if (result == 0) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }
    }

    public static void validateNonNullObject(Object object) {
        if (object == null) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }
    }
}
