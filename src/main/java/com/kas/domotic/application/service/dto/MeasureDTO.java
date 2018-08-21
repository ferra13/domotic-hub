package com.kas.domotic.application.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kas.domotic.domain.measure.MeasureType;

public class MeasureDTO {

	private final String id;
	private final Double value;
	private final Long measureTime;
	private final MeasureType type;

	@JsonCreator
	protected MeasureDTO(@JsonProperty("id") String id, @JsonProperty("value") Double value,
			@JsonProperty("data") Long measureTime, @JsonProperty("type") MeasureType type) {
		this.id = id;
		this.value = value;
		this.measureTime = measureTime;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	
	public Long getData() {
		return measureTime;
	}
	
	public Double getValue() {
		return value;
	}
	
	public MeasureType getType() {
		return type;
	}
	
	public static MeasureDTO of(String id, Double value, Long measureTime, MeasureType type) {
		return new MeasureDTO(id, value, measureTime, type);
	}
}
