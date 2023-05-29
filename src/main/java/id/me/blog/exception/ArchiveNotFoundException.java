package id.me.blog.exception;

/**
 * The exception class to handle when the archive is not found in database.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-30
 */
public class ArchiveNotFoundException extends RuntimeException {
    public ArchiveNotFoundException(String message){
        super(message);
    }
}
