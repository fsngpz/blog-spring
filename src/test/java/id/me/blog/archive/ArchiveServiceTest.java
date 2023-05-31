package id.me.blog.archive;

import id.me.blog.exception.ArchiveNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * The test class for Archive Service.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-31
 */
@SpringBootTest(classes = {ArchiveService.class})
class ArchiveServiceTest {

    @Autowired
    private ArchiveService archiveService;

    @MockBean
    private ArchiveRepository mockArchiveRepository;

    @Test
    void dependencyAreNotNull(){
        assertThat(this.archiveService).isNotNull();
        assertThat(this.mockArchiveRepository).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void create_FieldBodyEmpty(String body){
        ArchiveRequest mockRequest = new ArchiveRequest("Hello", body, "JUnit");

        // -- execute --
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> this.archiveService.create(mockRequest));

        assertThat(exception.getMessage()).isEqualTo("the field 'body' cannot be blank or empty");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void create_FieldTitleEmpty(String title){
        ArchiveRequest mockRequest = new ArchiveRequest(title, "The quick", "JUnit");

        // -- execute --
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> this.archiveService.create(mockRequest));

        assertThat(exception.getMessage()).isEqualTo("the field 'title' cannot be blank or empty");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void create_FieldAuthorEmpty(String author){
        ArchiveRequest mockRequest = new ArchiveRequest("Hello World", "The quick", author);

        // -- execute --
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> this.archiveService.create(mockRequest));

        assertThat(exception.getMessage()).isEqualTo("the field 'author' cannot be blank or empty");
    }

    @Test
    void create_success(){
        ArchiveRequest mockRequest = new ArchiveRequest("Hello World", "The quick", "JUnit");

        Archive mockArchive = new Archive(1L,
                mockRequest.getTitle(),
                mockRequest.getBody(),
                mockRequest.getAuthor(),
                OffsetDateTime.now(),
                mockRequest.getAuthor(),
                OffsetDateTime.now());

        Mockito.when(this.mockArchiveRepository.save(any())).thenReturn(mockArchive);

        var result = this.archiveService.create(mockRequest);

        assertThat(result.getTitle()).isNotNull().isEqualTo(mockArchive.getTitle());

        Mockito.verify(this.mockArchiveRepository, Mockito.atMostOnce()).save(any());
    }

    @Test
    void getArchive_ArchiveNotFound(){
        Long id = 1L;
        Mockito.when(this.mockArchiveRepository.findById(any())).thenReturn(Optional.empty());

        // -- execute --
        ArchiveNotFoundException exception = assertThrows(ArchiveNotFoundException.class,
                () -> this.archiveService.getArchive(id));

        assertThat(exception.getMessage()).isEqualTo(  String.format("no archive was found with id '%s'", id));
    }

    @Test
    void getArchive_Success(){
        Long id = 1L;

        Archive mockArchive = new Archive(
                id,
                "Hello World",
                "The quick",
                "JUnit",
                OffsetDateTime.now(),
                "JUnit",
                OffsetDateTime.now());
        Mockito.when(this.mockArchiveRepository.findById(any())).thenReturn(Optional.of(mockArchive));

        var result = this.archiveService.getArchive(id);

        assertThat(result).isNotNull().isEqualTo(result);
    }

    @Test
    void getAllArchive_Success(){
        Archive mockArchive1 = new Archive(
                1L,
                "Hello World",
                "The quick",
                "JUnit",
                OffsetDateTime.now(),
                "JUnit",
                OffsetDateTime.now());
        Archive mockArchive2 = new Archive(
                1L,
                "Hello World",
                "The quick",
                "JUnit",
                OffsetDateTime.now(),
                "JUnit",
                OffsetDateTime.now());
        Archive mockArchive3 = new Archive(
                1L,
                "Hello World",
                "The quick",
                "JUnit",
                OffsetDateTime.now(),
                "JUnit",
                OffsetDateTime.now());

        var archives = List.of(mockArchive1, mockArchive2, mockArchive3);

        Mockito.when(this.mockArchiveRepository.findAll(any(Pageable.class))).thenReturn(
                new PageImpl<>(archives));

        var result = this.archiveService.getAllArchive(Pageable.unpaged());

        assertThat(result.getContent().isEmpty()).isFalse();
        assertThat(result.getTotalElements()).isEqualTo(3);
    }

    @Test
    void getAllArchive_SuccessEmpty(){
        Mockito.when(this.mockArchiveRepository.findAll(any(Pageable.class))).thenReturn(
                Page.empty());

        var result = this.archiveService.getAllArchive(Pageable.unpaged());

        assertThat(result.getContent().isEmpty()).isTrue();
        assertThat(result.getTotalElements()).isEqualTo(0);
    }

    @Test
    void deleteArchive_Success(){
        Long id = 1L;

        Archive mockArchive = new Archive(
                id,
                "Hello World",
                "The quick",
                "JUnit",
                OffsetDateTime.now(),
                "JUnit",
                OffsetDateTime.now());
        Mockito.when(this.mockArchiveRepository.findById(any())).thenReturn(Optional.of(mockArchive));

        // -- execute --
        this.archiveService.deleteArchive(id);

        Mockito.verify(this.mockArchiveRepository, Mockito.atMostOnce()).delete(any());
    }

    @Test
    void deleteArchive_ArchiveNotFound(){
        Long id = 1L;
        Mockito.when(this.mockArchiveRepository.findById(any())).thenReturn(Optional.empty());

        // -- execute --
        ArchiveNotFoundException exception = assertThrows(ArchiveNotFoundException.class,
                () -> this.archiveService.deleteArchive(id));

        assertThat(exception.getMessage()).isEqualTo(  String.format("no archive was found with id '%s'", id));
    }

    @Test
    void updateArchive_Success(){
        Long id = 1L;
        ArchiveRequest mockRequest = new ArchiveRequest("Hello", "The Quick", "JUnit");

        Archive mockArchive = new Archive(id,
                mockRequest.getTitle(),
                mockRequest.getBody(),
                mockRequest.getAuthor(),
                OffsetDateTime.now(),
                mockRequest.getAuthor(),
                OffsetDateTime.now());

        Mockito.when(this.mockArchiveRepository.findById(any())).thenReturn(Optional.of(mockArchive));

        // -- execute --
        var result =  this.archiveService.updateArchive(id, mockRequest);

        assertThat(result.getTitle()).isEqualTo(mockRequest.getTitle());
        assertThat(result.getBody()).isEqualTo(mockRequest.getBody());
        assertThat(result.getPublishedBy()).isEqualTo(mockRequest.getAuthor());
    }

    @Test
    void updateArchive_NotFound(){
        Long id = 1L;
        ArchiveRequest mockRequest = new ArchiveRequest("Hello", "The Quick", "JUnit");
        Mockito.when(this.mockArchiveRepository.findById(any())).thenReturn(Optional.empty());

        // -- execute --
        ArchiveNotFoundException exception = assertThrows(ArchiveNotFoundException.class,
                () -> this.archiveService.updateArchive(id, mockRequest));

        assertThat(exception.getMessage()).isEqualTo(  String.format("no archive was found with id '%s'", id));
    }
}
