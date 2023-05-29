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

    /**
     * Handle the General Exception.
     *
     * @param e {@link Exception}.
     * @return Response Entity with {@link ErrorResponse}.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e){
        var response = ErrorResponse.create("UHNANDLED_EXCEPTION", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Handle the Illegal Arguments Exception.
     *
     * @param e {@link IllegalArgumentException}.
     * @return Response Entity with {@link ErrorResponse}.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIllegalArgsException(IllegalArgumentException e){
        var response = ErrorResponse.create("ILLEGAL_ARGS_EXCEPTION", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle the Authentication Exception.
     *
     * @param e {@link AuthenticationException}.
     * @return Response Entity with {@link ErrorResponse}.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e){
        var response = ErrorResponse.create("USERNAME_OR_PASSWORD_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    /**
     * Handle the Archive Not Found Exception.
     * @param e {@link ArchiveNotFoundException}.
     * @return Response Entity with {@link ErrorResponse}.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleArchiveNotFoundException(ArchiveNotFoundException e){
        var response = ErrorResponse.create("ARCHIVE_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Handle the Duplicate Archive Exception.
     * @param e {@link DuplicateArchiveException}.
     * @return Response Entity with {@link ErrorResponse}.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDuplicateArchiveException(DuplicateArchiveException e){
        var response = ErrorResponse.create("DUPLICATE_ARCHIVE", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
