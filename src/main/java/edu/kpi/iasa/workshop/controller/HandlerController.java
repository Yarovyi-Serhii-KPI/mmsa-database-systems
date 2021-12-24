package edu.kpi.iasa.workshop.controller;

import edu.kpi.iasa.workshop.exception.*;
import edu.kpi.iasa.workshop.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class HandlerController {

    private final MessageSource messageSource;

    public HandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value
            = { PlaylistNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            PlaylistNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Playlist Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { RoleNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            RoleNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Role Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { SongNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            SongNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Song Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { ArtistNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            ArtistNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Artist Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { AlbumNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            AlbumNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Album Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler({UserNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Void> handleUserNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<Error>> validationExceptionHandler(ConstraintViolationException ex, WebRequest request) {
        log.info("ex:", ex.getConstraintViolations().toArray());
        List<Error> errors = ex.getConstraintViolations().stream().map(violation ->
                Error.builder().description(violation.getPropertyPath() + " invalid. " +
                                messageSource.getMessage(violation.getMessage(), null, request.getLocale()))
                        .code("Bad Request").build()
        ).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

}