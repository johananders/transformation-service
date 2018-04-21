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
    @Column(nullable = false, length = 1000)
    private String original;
    @Column(nullable = false, length = 1000)
    private String result;
    @Column(nullable = false)
    private Instant created;

    @SuppressWarnings("unused")
    private Transformation() {
    }

    private Transformation(final Builder builder) {
        this.id = builder.id;
        this.original = Objects.requireNonNull(builder.original);
        this.result = Objects.requireNonNull(builder.result);
        this.created = builder.created;
    }

    public static Builder builder() {
        return new Builder();
    }

    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getOriginal() {
        return original;
    }

    public String getResult() {
        return result;
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

    @Override
    public String toString() {
        return "Transformation{" +
            "id=" + id +
            ", original='" + original + '\'' +
            ", result='" + result + '\'' +
            ", created=" + created +
            '}';
    }

    public static final class Builder {

        private Long id;
        private String original;
        private String result;
        private Instant created;

        private Builder() {
        }

        public Transformation build() {
            return new Transformation(this);
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

        public Builder created(final Instant created) {
            this.created = created;
            return this;
        }
    }
}
