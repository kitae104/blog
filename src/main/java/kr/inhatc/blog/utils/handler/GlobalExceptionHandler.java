package kr.inhatc.blog.utils.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "<h1>";
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        return "<h1>" + e.getMessage() + "<h1>";
    }
}
