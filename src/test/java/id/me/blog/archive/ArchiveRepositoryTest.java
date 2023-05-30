package id.me.blog.archive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The database or repository test class for {@link Archive}.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-30
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
class ArchiveRepositoryTest {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Container
    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(
            "postgres:14.5").withUsername("postgres");

    @DynamicPropertySource
    static void initProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);

        final String randomSchemaName = "blog";
        registry.add("spring.flyway.schemas", () -> randomSchemaName);
        registry.add("spring.jpa.properties.hibernate.default.schema", () -> randomSchemaName);
        registry.add("spring.flyway.enabled", () -> true);
    }

    @Test
    void checkDependency(){
        assertThat(this.archiveRepository).isNotNull();
    }

    @BeforeEach
    public void init(){
        Archive archive = new Archive(
                null,
                "Hello World",
                "The quickest brown jump over",
                "J Uniit",
                OffsetDateTime.now(),
                "J Uniit",
                OffsetDateTime.now());

        this.archiveRepository.save(archive);}

    @Test
    void checkDatabase(){
        List<Archive> archives = this.archiveRepository.findAll();

        assertThat(archives).isNotEmpty();
    }
}
