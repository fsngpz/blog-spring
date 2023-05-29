package id.me.blog.exception;

/**
 * The exception class to handle when the archive is already exist in database.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-30
 */
public class DuplicateArchiveException extends RuntimeException{

    public DuplicateArchiveException(String message){
        super(message);
    }
}
