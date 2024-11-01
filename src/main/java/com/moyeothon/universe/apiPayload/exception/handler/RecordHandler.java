package com.moyeothon.universe.apiPayload.exception.handler;

import com.moyeothon.universe.apiPayload.code.BaseErrorCode;
import com.moyeothon.universe.apiPayload.exception.GeneralException;

public class RecordHandler extends GeneralException {
    public RecordHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
