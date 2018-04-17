package com.wordsmith.transformation.api;

import com.wordsmith.transformation.api.model.TransformationRequest;
import com.wordsmith.transformation.api.model.TransformationResponse;
import com.wordsmith.transformation.service.TransformationService;
import java.net.URI;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/transformations/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TransformationController {

    @Autowired
    private TransformationService transformationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransformationResponse> create(
        @RequestBody @Valid @NotNull final TransformationRequest transformationRequest
    ) {
        final TransformationResponse transformationResponse = transformationService.create(transformationRequest);

        final URI uri = buildLocationUri(transformationResponse);

        return ResponseEntity
            .created(uri)
            .body(transformationResponse);
    }

    @GetMapping("/{id}")
    public TransformationResponse get(@PathVariable("id") @NotNull final Long id) {
        return transformationService.get(id);
    }

    private URI buildLocationUri(final TransformationResponse transformationResponse) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(transformationResponse.getId())
            .toUri();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notfoundMapper(final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> internalServerErrorMapper(final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
