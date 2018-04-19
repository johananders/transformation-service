package com.wordsmith.transformation.api;

import com.wordsmith.transformation.api.model.TransformationRequest;
import com.wordsmith.transformation.api.model.TransformationResponse;
import com.wordsmith.transformation.service.TransformationService;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationController.class);

    private final TransformationService transformationService;

    @Autowired
    public TransformationController(final TransformationService transformationService) {
        this.transformationService = Objects.requireNonNull(transformationService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
        value = "Creates a text transformation",
        notes = "Reverses all individual words, keeping the original order of the words.\n\n"
            + "Example: \"\"The red fox crosses the ice, intent on none of my business.\" => "
            + "\"ehT der xof sessorc eht eci, tnetni no enon fo ym ssenisub.\""
    )
    public ResponseEntity<TransformationResponse> create(
        @RequestBody @Valid @NotNull final TransformationRequest transformationRequest
    ) {
        LOGGER.debug("Create request: {}", transformationRequest);

        final TransformationResponse transformationResponse = transformationService.create(transformationRequest);

        LOGGER.debug("Create response: {}", transformationResponse);

        final URI uri = buildLocationUri(transformationResponse);

        return ResponseEntity
            .created(uri)
            .body(transformationResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a transformation by id")
    public TransformationResponse get(@PathVariable("id") @NotNull final Long id) {
        LOGGER.debug("Get request: {}", id);

        final TransformationResponse transformationResponse = transformationService.get(id);

        LOGGER.debug("Get response: {}", transformationResponse);

        return transformationResponse;
    }

    private URI buildLocationUri(final TransformationResponse transformationResponse) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(transformationResponse.getId())
            .toUri();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> mapToNotFound(final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
