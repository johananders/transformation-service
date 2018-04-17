package com.wordsmith.transformation.service;

import com.wordsmith.transformation.api.model.TransformationResponse;
import com.wordsmith.transformation.domain.Transformation;

public class TransformationMapper {

    private TransformationMapper() {
    }

    static TransformationResponse toTransformationResponse(final Transformation transformation) {
        return TransformationResponse.builder()
            .id(transformation.getId())
            .original(transformation.getOriginal())
            .transformed(transformation.getTransformed())
            .build();
    }
}
