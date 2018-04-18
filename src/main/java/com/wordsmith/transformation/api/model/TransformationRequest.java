package com.wordsmith.transformation.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformationRequest {

    @NotNull
    @Size(min = 1, max = 1000)
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
