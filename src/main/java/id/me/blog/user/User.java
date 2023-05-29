package id.me.blog.user;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Ferdinand Sangap
 * @since 2023-05-27
 */
@Setter
@Getter
@Entity
@Table(name = "authentication", schema = "blog")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
    private String roles;
}
