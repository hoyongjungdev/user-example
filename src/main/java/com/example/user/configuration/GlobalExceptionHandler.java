package com.example.user.configuration;

import com.example.user.exception.BaseException;
import com.example.user.exception.ErrorCode;
import com.example.user.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BaseException.class})
    protected ResponseEntity<ErrorResponse> handleException(BaseException exception) {
        log.error("Exception : {}", exception.getErrorCode());
        return ErrorResponse.toResponseEntity(exception);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        HttpStatus statusOverride = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(statusOverride)
                .body(
                        ErrorResponse.builder()
                                .success(false)
                                .status(statusOverride.name())
                                .code(ErrorCode.VALIDATION_ERROR.name())
                                .message("올바르지 않은 입력입니다.")
                                .build()
                );
    }
}