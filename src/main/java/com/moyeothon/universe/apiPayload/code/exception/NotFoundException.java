package com.moyeothon.universe.apiPayload.code.exception;

import com.moyeothon.universe.apiPayload.code.error.ErrorCode;
import com.moyeothon.universe.apiPayload.code.exception.CustomException;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}