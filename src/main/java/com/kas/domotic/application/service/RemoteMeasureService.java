package com.kas.domotic.application.service;

import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.application.service.dto.StationDTO;

public interface RemoteMeasureService {

	public Boolean createMeasure(String measures);
	
	public String getMeasure(StationDTO station);
	
	public ImmutableList<MeasureDTO> getCurrentMeasure(String stationId);
}
