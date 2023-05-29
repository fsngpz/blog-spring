package id.me.blog.archive;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The repository class for Archive.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-29
 */
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
}
