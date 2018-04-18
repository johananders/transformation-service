package com.wordsmith.transformation.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformationResponse {

    private Long id;
    private String original;
    private String result;

    @SuppressWarnings("unused")
    private TransformationResponse() {
    }

    private TransformationResponse(final Builder builder) {
        this.id = Objects.requireNonNull(builder.id);
        this.original = Objects.requireNonNull(builder.original);
        this.result = Objects.requireNonNull(builder.result);
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
    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "TransformationResponse{" +
            "id=" + id +
            ", original='" + original + '\'' +
            ", result='" + result + '\'' +
            '}';
    }

    public static final class Builder {
        private Long id;
        private String original;
        private String result;

        private Builder() {
        }

        public TransformationResponse build() {
            return new TransformationResponse(this);
        }

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder original(final String original) {
            this.original = original;
            return this;
        }

        public Builder result(final String result) {
            this.result = result;
            return this;
        }
    }

}
