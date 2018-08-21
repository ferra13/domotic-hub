package com.kas.domotic.application.service;

import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.domain.measure.Position;

public interface MeasureService {

	public ImmutableList<MeasureDTO> getAllMeasure();
	
	public MeasureDTO getMeasure(String id);

	public ImmutableList<MeasureDTO> getAllTemperature();
	
	public ImmutableList<MeasureDTO> getAllTemperature(Position position);

	public ImmutableList<MeasureDTO> getAllUmidity();

	public ImmutableList<MeasureDTO> getAllPressure();
	
	public ImmutableList<MeasureDTO> getAllLight();
	
}
