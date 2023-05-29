package id.me.blog.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public NewArchiveResponse create(@RequestBody NewArchiveRequest request){
        return this.archiveService.create(request);
    }
}
