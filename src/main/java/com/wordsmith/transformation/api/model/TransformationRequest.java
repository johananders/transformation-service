package com.wordsmith.transformation.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformationRequest {

    @NotEmpty
    private String text;

    @SuppressWarnings("unused")
    private TransformationRequest() {
    }

    private TransformationRequest(final String text) {
        this.text = Objects.requireNonNull(text);
    }

    public static TransformationRequest of(final String text) {
        return new TransformationRequest(text);
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "TransformationRequest{" +
            "text='" + text + '\'' +
            '}';
    }
}
