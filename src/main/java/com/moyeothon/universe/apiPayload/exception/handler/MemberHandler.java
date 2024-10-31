package com.moyeothon.universe.apiPayload.exception.handler;

import com.moyeothon.universe.apiPayload.code.BaseErrorCode;
import com.moyeothon.universe.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
