package com.B305.ogym.common.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    // 5xx error handler : 서버에서 발생한 전반적인 에러에 대한 핸들러
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .Error(request.getDescription(false))
            .message(ex.getMessage())
            .build();
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 이미 존재하는 유저 가입에 대한 에러 핸들러
    @ExceptionHandler(DuplicateUserException.class)
    public final ResponseEntity<Object> handleDuplicateUserException(Exception ex,
        WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .Error(request.getDescription(false))
            .message(ex.getMessage())
            .build();
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // 존재하지 않는 유저 정보 조회에 대한 에러 핸들러
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex,
        WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .Error(request.getDescription(false))
            .message(ex.getMessage())
            .build();
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Valid 조건을 만족하지 못한 요청에 대한 에러 핸들러
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .message("Vaildation Failed")
            .Error(ex.getBindingResult().toString())
            .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
