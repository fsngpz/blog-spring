package id.me.blog.exception;

import java.time.OffsetDateTime;

/**
 * The model class for response error.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-29
 */
public record ErrorResponse(OffsetDateTime timestamp,
                            ErrorData error) {

    public static ErrorResponse create(String errorCode, String errorMessage) {
        return new ErrorResponse(OffsetDateTime.now(), new ErrorData(errorCode, errorMessage));
    }}
