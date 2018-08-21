package com.kas.domotic.domain.measure;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.kas.domotic.domain.Measure;
import com.kas.domotic.domain.remote.Request;
import com.kas.domotic.domain.station.Station;

@Entity
public class TemperatureMeasure extends Measure {

	@Column
	@Enumerated(value=EnumType.STRING)
	private Position position;
	
	private TemperatureMeasure() {
		
	}

	public Position position() {
		return position;
	}
	
	private TemperatureMeasure( LocalDateTime measureTime, Double value, Position position, Request request, Station station) {
		super(measureTime, value, request, station);
		this.position = position;
	}
	
	public static TemperatureMeasure of(LocalDateTime measureTime, Double temperature, Position position, Request request, Station station) {
		return new TemperatureMeasure(measureTime, temperature, position, request, station);
	}
}
