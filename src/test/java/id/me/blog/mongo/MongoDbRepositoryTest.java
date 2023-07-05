package id.me.blog.mongo;

import id.me.blog.MongoContainersConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ferdinand Sangap
 * @since 2023-07-05
 */
@Testcontainers
@DataMongoTest
@ContextConfiguration(classes = MongoContainersConfig.class)
@ActiveProfiles("test")
class MongoDbRepositoryTest {

    @Autowired
    private MongoDbRepository mongoDbRepository;

    @Test
    void init(){
        var mongo = new MongoDb();
        mongo.setId("aaaaa");

        mongo = mongoDbRepository.save(mongo);

        assertThat(mongo).isNotNull();
    }

}
