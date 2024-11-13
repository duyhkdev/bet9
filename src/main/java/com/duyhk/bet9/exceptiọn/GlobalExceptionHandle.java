package com.duyhk.bet9.exceptiọn;

import com.duyhk.bet9.dto.ResponseDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseDTO<Void> handleRuntimeException(RuntimeException e){
        return ResponseDTO.<Void>builder()
                .status(500)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseDTO<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        return ResponseDTO.<Void>builder()
                .status(400)
                .message(e.getFieldError().getDefaultMessage())
                .build();
    }
}
