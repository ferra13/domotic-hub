package com.kas.domotic.application.service.assembler;

import com.kas.domotic.application.service.dto.StationDTO;
import com.kas.domotic.domain.station.Station;

public class StationAssembler {

	public static Station fromDTO(StationDTO dto) {
		return null;
	}
	
	public static StationDTO fromStation(Station station) {
		return StationDTO.of(station.id(), station.name(), station.macAddress(), 
				station.latitude(), station.longitude(), station.url());
	}
}
