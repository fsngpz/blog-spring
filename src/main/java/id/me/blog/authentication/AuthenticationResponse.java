package id.me.blog.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for response authentication.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String bearerToken;
}
