package com.webtoon.coding.core.exception;

import com.webtoon.coding.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handler(Exception e) {
        MsgType msgType = MsgType.ServerError;
        log.error(e.getMessage(), e.getCause(), e);
        return ErrorResponse.of(msgType.getCode(), msgType.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public ErrorResponse handler(BaseException e) {
        MsgType msgType = e.getMsgType();
        log.error(e.getMessage(), e.getCause(), e);
        return ErrorResponse.of(msgType.getCode(), msgType.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handler(HttpMessageNotReadableException e) {
        MsgType msgType = MsgType.EmptyRequestBody;
        log.error(e.getMessage(), e.getCause(), e);
        return ErrorResponse.of(msgType.getCode(), msgType.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse handler(BindException e) {
        MsgType msgType = MsgType.EmptyRequestBody;
        log.error(e.getMessage(), e.getCause(), e);
        return ErrorResponse.of(msgType.getCode(), msgType.getMessage());
    }

}
