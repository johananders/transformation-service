package com.wordsmith.transformation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.wordsmith.transformation.api.model.TransformationRequest;
import com.wordsmith.transformation.api.model.TransformationResponse;
import com.wordsmith.transformation.dao.TransformationRepository;
import com.wordsmith.transformation.domain.Transformation;
import java.time.Instant;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransformationServiceTest {

    @InjectMocks
    private TransformationService transformationService;
    @Mock
    private TransformationRepository transformationRepository;
    @Spy
    private ReverseService reverseService;

    @Test
    public void createTransformation() {
        final Transformation transformation = Transformation.builder()
            .id(1L)
            .original("")
            .result("")
            .created(Instant.now())
            .build();
        when(transformationRepository.save(any(Transformation.class))).thenReturn(transformation);

        final TransformationResponse transformationResponse =
            transformationService.create(TransformationRequest.of(""));

        assertThat(transformationResponse.getId()).isEqualTo(1L);
    }

    @Test
    public void getExistingTransformation() {
        final Transformation transformation = Transformation.builder()
            .id(1L)
            .original("")
            .result("")
            .created(Instant.now())
            .build();
        when(transformationRepository.findById(eq(1L))).thenReturn(Optional.of(transformation));

        final TransformationResponse transformationResponse = transformationService.get(1L);

        assertThat(transformationResponse.getId()).isEqualTo(1L);
    }

    @Test
    public void getNonExistingTransformation() {
        when(transformationRepository.findById(eq(1L))).thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
            .isThrownBy(() -> transformationService.get(1L));
    }
}