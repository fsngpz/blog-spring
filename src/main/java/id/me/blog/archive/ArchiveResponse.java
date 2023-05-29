package id.me.blog.archive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * The response class for archive.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveResponse {
    private String title;
    private String body;
    private String publishedBy;
    private OffsetDateTime publishedAt;
    private String editedBy;
    private OffsetDateTime editedAt;
}
