package com.wordsmith.transformation.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import com.wordsmith.transformation.domain.Transformation;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransformationRepositoryIntegrationTest {

    @Autowired
    private TransformationRepository transformationRepository;

    @After
    public void after() {
        transformationRepository.deleteAll();
    }

    @Test
    public void shouldSaveAndGetTransformation() {
        final Transformation transformation = Transformation.builder()
            .original("a")
            .result("b")
            .build();

        final Transformation saved = transformationRepository.save(transformation);
        final Transformation fetched = transformationRepository.findById(saved.getId())
            .orElseThrow(AssertionError::new);

        assertThat(fetched.getId()).isEqualTo(saved.getId());
        assertThat(fetched.getCreated()).isEqualTo(saved.getCreated());
        assertThat(fetched.getOriginal()).isEqualTo("a");
        assertThat(fetched.getResult()).isEqualTo("b");
    }

    @Test
    public void getNonExistingEntityReturnsEmptyOptional() {
        final Optional<Transformation> result =
            transformationRepository.findById(1000000000L);

        assertThat(result).isEmpty();
    }

    @Test
    public void getAllEntities() {
        final int numberOfEntities = 10;
        final List<Transformation> entities = IntStream.range(0, numberOfEntities)
            .mapToObj(i ->
                Transformation.builder()
                    .original(Integer.toString(i))
                    .result(Integer.toString(i))
                    .build()
            )
            .collect(ImmutableList.toImmutableList());

        transformationRepository.saveAll(entities);

        final List<Transformation> allEntities = transformationRepository.findAll();
        assertThat(allEntities).hasSize(numberOfEntities);
    }
}