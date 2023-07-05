package id.me.blog.mongo;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ferdinand Sangap
 * @since 2023-07-05
 */
@Document(collection = "mongo_db")
@Setter
@Getter
@NoArgsConstructor
public class MongoDb {
    @Id
    private String id;
}
