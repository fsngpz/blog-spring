package id.me.blog.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

/**
 * The service class for {@link Archive}.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-29
 */
@RequiredArgsConstructor
@Service
public class ArchiveService {

    private final ArchiveRepository archiveRepository;

    public NewArchiveResponse create(NewArchiveRequest request){
        // -- validate the field 'body' is  not empty or blank --
        if (request.getBody().isBlank() || request.getBody().isEmpty()){
            throw new IllegalArgumentException("the field 'body' cannot be blank or empty");
        }

        // -- validate the field 'title' is  not empty or blank --
        if (request.getTitle().isBlank() || request.getTitle().isEmpty()){
            throw new IllegalArgumentException("the field 'title' cannot be blank or empty");
        }

        // -- validate the field 'author' is  not empty or blank --
        if (request.getAuthor().isBlank() || request.getAuthor().isEmpty()){
            throw new IllegalArgumentException("the field 'author' cannot be blank or empty");
        }

        Archive archive = new Archive(null,
                                        request.getTitle(),
                                        request.getBody(),
                                        request.getAuthor(),
                                        OffsetDateTime.now());

        this.archiveRepository.save(archive);

        return new NewArchiveResponse(
                archive.getId(),
                archive.getTitle(),
                archive.getBody(),
                archive.getPublishedBy(),
                archive.getPublishedAt());
    }
}
