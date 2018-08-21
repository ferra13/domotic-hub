package com.kas.domotic.application.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kas.domotic.domain.measure.RemoteMeasureType;

public class RemoteMeasureDTO {

	private final RemoteMeasureType type;
	private final Double value;
	
	@JsonCreator
	private RemoteMeasureDTO ( @JsonProperty("type") RemoteMeasureType type,
			@JsonProperty("value") Double value) {
		this.type = type;
		this.value = value;
	}
	
	public RemoteMeasureType getType() {
		return type;
	}
	
	public Double getValue() {
		return value;
	}
	
	public static RemoteMeasureDTO of(RemoteMeasureType type, Double value) {
		return new RemoteMeasureDTO(type, value);
	}
}
