package id.me.blog.archive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for archive request.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveRequest {
    private String title;
    private String body;
    private String author;
}
