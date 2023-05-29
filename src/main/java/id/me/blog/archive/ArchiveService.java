package id.me.blog.archive;

import id.me.blog.exception.ArchiveNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * function to handle interaction create new {@link Archive}.
     * @param request the {@link NewArchiveRequest} body.
     * @return the {@link NewArchiveResponse} instance.
     */
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

        // -- persist to database --
        this.archiveRepository.save(archive);

        return new NewArchiveResponse(
                archive.getId(),
                archive.getTitle(),
                archive.getBody(),
                archive.getPublishedBy(),
                archive.getPublishedAt());
    }

    /**
     * function to handle interaction to get the {@link Archive} by id.
     * @param id the archive unique identifier.
     * @return the {@link ArchiveResponse} instance.
     */
    public ArchiveResponse getArchive(Long id){
        // -- find the archive by id, if not found then throw an exception --
        Archive archive = this.archiveRepository.findById(id).orElseThrow(
                () -> new ArchiveNotFoundException(
                        String.format("no archive was found with id '%s'", id)));

        return this.toResponse(archive);
    }

    /**
     * function to handle interaction to get all archive.
     * @param pageable the {@link Pageable}.
     * @return the {@link Page} of {@link ArchiveResponse}
     */
    public Page<ArchiveResponse> getAllArchive(Pageable pageable){
        // -- find all in database then map it to response --
       return this.archiveRepository.findAll(pageable).map(this::toResponse);
    }

    /**
     * private function to map the {@link Archive} to {@link ArchiveResponse}.
     * @param archive the {@link Archive} instance.
     * @return the {@link ArchiveResponse} instance.
     */
    private ArchiveResponse toResponse(Archive archive){
        return new ArchiveResponse(
                archive.getTitle(),
                archive.getBody(),
                archive.getPublishedBy(),
                archive.getPublishedAt());
    }
}
