package id.me.blog.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The rest controller to handle all interaction for archive.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/v1/api/archive")
@RequiredArgsConstructor
public class ArchiveController {

    private final ArchiveService archiveService;

    /**
     * handle request to create new {@link Archive}.
     * @param request the {@link ArchiveRequest} instance.
     * @return the {@link NewArchiveResponse}.
     */
    @PostMapping
    public NewArchiveResponse create(@RequestBody ArchiveRequest request){
        return this.archiveService.create(request);
    }

    /**
     * handle the request to get the {@link Archive} by id.
     * @param id the archive unique identifier.
     * @return the {@link ArchiveResponse}.
     */
    @GetMapping("/{id}")
    public ArchiveResponse getById(@PathVariable Long id){
        // -- if the 'id' is null then throw an exception --
        if (id == null){
            throw new IllegalArgumentException("required value for 'id'");
        }

        Archive archive = this.archiveService.getArchive(id);

        return new ArchiveResponse(
                archive.getTitle(),
                archive.getBody(),
                archive.getPublishedBy(),
                archive.getPublishedAt(),
                archive.getEditedBy(),
                archive.getEditedAt());
    }

    /**
     * handle the request to get the list of {@link Archive}.
     * @param sortBy parameter to sort the data.
     * @param sortDirection parameter to set the direction of sorting.
     * @param pageIndex the index of page.
     * @param pageSize the size of page.
     * @return the {@link Page} of {@link ArchiveResponse}.
     */
    @GetMapping
    public Page<ArchiveResponse> getAll(
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String sortDirection,
            @RequestParam(required = false, defaultValue = "0") int pageIndex,
            @RequestParam(required = false, defaultValue = "10") int pageSize
    ){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        return this.archiveService.getAllArchive(pageable);
    }

    /**
     * handle request to delete the {@link Archive} by id.
     * @param id the archive unique identifier.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        // -- if the 'id' is null then throw an exception --
        if (id == null){
            throw new IllegalArgumentException("required value for 'id'");
        }

        this.archiveService.deleteArchive(id);
    }

    /**
     * handle request to update the {@link Archive}.
     * @param id the archive unique identifier.
     * @param request the {@link  ArchiveRequest} instance.
     * @return the {@link ArchiveResponse} instance.
     */
    @PutMapping("/{id}")
    public ArchiveResponse update(@PathVariable Long id, @RequestBody ArchiveRequest request){
        // -- if the 'id' is null then throw an exception --
        if (id == null){
            throw new IllegalArgumentException("required value for 'id'");
        }

        return this.archiveService.updateArchive(id, request);
    }
}
