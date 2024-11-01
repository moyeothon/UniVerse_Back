package com.moyeothon.universe.apiPayload.exception.handler;

import com.moyeothon.universe.apiPayload.code.BaseErrorCode;
import com.moyeothon.universe.apiPayload.exception.GeneralException;

public class FileUploadHandler extends GeneralException {
    public FileUploadHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
