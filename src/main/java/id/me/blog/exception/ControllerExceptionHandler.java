package id.me.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The handler class for any exception.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-29
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e){
        var response = ErrorResponse.create("UHNANDLED_EXCEPTION", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIllegalArgsException(IllegalArgumentException e){
        var response = ErrorResponse.create("ILLEGAL_ARGS_EXCEPTION", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(AuthenticationException e){
        var response = ErrorResponse.create("USERNAME_OR_PASSWORD_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
