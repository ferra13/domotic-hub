package com.kas.domotic.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kas.domotic.domain.Measure;

@NoRepositoryBean
public interface MeasureRepository<T extends Measure> extends PagingAndSortingRepository<T, String>, JpaSpecificationExecutor<T> {

}
