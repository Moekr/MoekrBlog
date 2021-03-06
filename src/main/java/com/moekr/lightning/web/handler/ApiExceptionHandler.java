package com.moekr.lightning.web.handler;

import com.moekr.lightning.util.ServiceException;
import com.moekr.lightning.util.ToolKit;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice("com.moekr.blog.web.controller.api")
public class ApiExceptionHandler {
    private static final int DEFAULT_ERROR_CODE = 500;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handle(Exception exception) {
        int error = DEFAULT_ERROR_CODE;
        if (exception instanceof ServiceException) {
            error = ((ServiceException) exception).getError();
        }
        return ToolKit.assemblyResponseBody(error, exception.getMessage());
    }
}
