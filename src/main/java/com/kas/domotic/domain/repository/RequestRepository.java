package com.kas.domotic.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kas.domotic.domain.remote.Request;

public interface RequestRepository extends PagingAndSortingRepository<Request, String>{

}
