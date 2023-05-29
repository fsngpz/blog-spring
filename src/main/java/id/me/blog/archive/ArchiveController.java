package id.me.blog.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
     * @param request the {@link NewArchiveRequest} instance.
     * @return the {@link NewArchiveResponse}.
     */
    @PostMapping
    public NewArchiveResponse create(@RequestBody NewArchiveRequest request){
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

        return this.archiveService.getArchive(id);
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
}
