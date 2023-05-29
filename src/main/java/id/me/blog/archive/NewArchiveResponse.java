package id.me.blog.archive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * The model class for new archive response.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewArchiveResponse {

    private Long archiveId;
    private String title;
    private String body;
    private String publishedBy;
    private OffsetDateTime publishedAt;
}
