package com.wordsmith.transformation.service;

import com.wordsmith.transformation.api.model.TransformationRequest;
import com.wordsmith.transformation.api.model.TransformationResponse;
import com.wordsmith.transformation.dao.TransformationRepository;
import com.wordsmith.transformation.domain.Transformation;
import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransformationService {

    private final TransformationRepository transformationRepository;
    private final ReverseService reverseService;

    @Autowired
    public TransformationService(
        final TransformationRepository transformationRepository,
        final ReverseService reverseService
    ) {
        this.transformationRepository = Objects.requireNonNull(transformationRepository);
        this.reverseService = Objects.requireNonNull(reverseService);
    }

    public TransformationResponse create(final TransformationRequest transformationRequest) {
        final String reversed = reverseService.reverse(transformationRequest.getText());

        final Transformation transformation = Transformation.builder()
            .original(transformationRequest.getText())
            .result(reversed)
            .build();

        final Transformation persisted = transformationRepository.save(transformation);

        return TransformationMapper.toTransformationResponse(persisted);
    }

    public TransformationResponse get(final Long id) {
        return transformationRepository.findById(id)
            .map(TransformationMapper::toTransformationResponse)
            .orElseThrow(() -> new EntityNotFoundException("Id=" + id + " not found"));
    }
}
