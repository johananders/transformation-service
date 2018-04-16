package com.wordsmith.transformation.domain;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Transformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String original;
    @Column(nullable = false)
    private String transformed;
    @Column(nullable = false)
    private Instant created;

    private Transformation() {
    }

    private Transformation(final Builder builder) {
        this.original = builder.original;
        this.transformed = builder.transformed;
    }

    public static Builder builder() {
        return new Builder();
    }

    @PrePersist
    public void prePersist() {
        created = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getOriginal() {
        return original;
    }

    public String getTransformed() {
        return transformed;
    }

    public Instant getCreated() {
        return created;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transformation that = (Transformation) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class Builder {
        private String original;
        private String transformed;

        private Builder() {
        }

        public Transformation build() {
            return new Transformation(this);
        }

        public Builder original(final String original) {
            this.original = original;
            return this;
        }

        public Builder transformed(final String transformed) {
            this.transformed = transformed;
            return this;
        }
    }
}
