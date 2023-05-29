package id.me.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for root.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-27
 */
@RestController
@RequestMapping("/v1/api")
public class RootController {

    @RequestMapping("/")
    public ResponseEntity<String> root(){
        return ResponseEntity.ok("OK");
    }
}
