package com.kas.domotic.domain.measure;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.kas.domotic.domain.Measure;
import com.kas.domotic.domain.remote.Request;
import com.kas.domotic.domain.station.Station;

@Entity
public class PressureMeasure extends Measure{
	
	private PressureMeasure() {
	}
	
	private PressureMeasure(LocalDateTime measureTime, Double value, Request request, Station station) {
		super(measureTime, value, request, station);
	}
	
	public static PressureMeasure of(LocalDateTime measureTime, Double pressure, Request request, Station station) {
		return new PressureMeasure(measureTime, pressure, request, station);
	}
}
