package com.kas.domotic.application.service;

import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.application.service.dto.PageDTO;
import com.kas.domotic.domain.measure.Position;

public interface MeasureService {

	//Nonsense with multi device
	//public PageDTO<MeasureDTO> getAllMeasure(PageParams params);
	
	public MeasureDTO getMeasure(String id);

	public PageDTO<MeasureDTO> getAllTemperature(PageParams params);
	
	public PageDTO<MeasureDTO> getAllTemperature(Position position, PageParams params);

	public PageDTO<MeasureDTO> getAllUmidity(PageParams params);

	public PageDTO<MeasureDTO> getAllPressure(PageParams params);
	
	public PageDTO<MeasureDTO> getAllLight(PageParams params);
	
}
