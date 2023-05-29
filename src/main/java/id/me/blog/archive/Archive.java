package id.me.blog.archive;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * The entity for archive.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-29
 */
@Getter
@Setter
@Entity
@Table(name = "archives", schema = "blog")
@AllArgsConstructor
@RequiredArgsConstructor
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private Long id;

    private String title;
    private String body;

    private String publishedBy;
    private OffsetDateTime publishedAt;
}
