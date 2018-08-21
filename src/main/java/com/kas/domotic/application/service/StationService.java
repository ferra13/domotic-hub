package com.kas.domotic.application.service;

import java.util.List;

import com.kas.domotic.application.service.dto.StationDTO;
import com.kas.domotic.domain.station.Station;

public interface StationService {

	public Station registerStation(StationDTO dto);
	
	public StationDTO get(String stationId);
	
	public List<StationDTO> getAll();
}
