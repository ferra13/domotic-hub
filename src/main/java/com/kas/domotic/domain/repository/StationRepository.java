package com.kas.domotic.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kas.domotic.domain.station.Station;

public interface StationRepository extends PagingAndSortingRepository<Station, String> {

	public Station findByName(String name);
	public Station findByNameAndLatitudeAndLongitude(String name, Double latitude, Double longitude);
}  
