package com.wordsmith.transformation.dao;

import com.wordsmith.transformation.domain.Transformation;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformationRepository extends PagingAndSortingRepository<Transformation, Long> {

    List<Transformation> findAll();

}
