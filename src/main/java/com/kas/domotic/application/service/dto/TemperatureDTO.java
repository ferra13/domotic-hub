package com.kas.domotic.application.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kas.domotic.domain.measure.MeasureType;
import com.kas.domotic.domain.measure.Position;


public class TemperatureDTO extends MeasureDTO {

	private final Position position;
	
	@JsonCreator
	private TemperatureDTO(String id, Double value,
			Long measureTime, MeasureType type, 
			@JsonProperty("position") Position position) {
		super(id, value, measureTime, type);
		this.position = position;
	} 		
	
	public Position getPosition() {
		return position;
	}
	
	public static TemperatureDTO of(String id, Double value, Long measureTime, MeasureType type, Position position) {
		return new TemperatureDTO(id, value, measureTime, type, position);
		
	}
}
