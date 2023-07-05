package id.me.blog.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Ferdinand Sangap
 * @since 2023-07-05
 */
interface MongoDbRepository extends MongoRepository<MongoDb, String> {
}
