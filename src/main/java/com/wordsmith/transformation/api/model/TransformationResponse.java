package com.wordsmith.transformation.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformationResponse {

    private Long id;
    private String original;
    private String transformed;

    @SuppressWarnings("unused")
    private TransformationResponse() {
    }

    private TransformationResponse(Builder builder) {
        this.id = Objects.requireNonNull(builder.id);
        this.original = Objects.requireNonNull(builder.original);
        this.transformed = Objects.requireNonNull(builder.transformed);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public String getOriginal() {
        return original;
    }

    @SuppressWarnings("unused")
    public String getTransformed() {
        return transformed;
    }

    public static final class Builder {
        private Long id;
        private String original;
        private String transformed;

        private Builder() {
        }

        public TransformationResponse build() {
            return new TransformationResponse(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder original(String original) {
            this.original = original;
            return this;
        }

        public Builder transformed(String transformed) {
            this.transformed = transformed;
            return this;
        }
    }
}
