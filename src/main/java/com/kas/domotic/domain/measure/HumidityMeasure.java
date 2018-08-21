package com.kas.domotic.domain.measure;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.kas.domotic.domain.Measure;
import com.kas.domotic.domain.remote.Request;
import com.kas.domotic.domain.station.Station;

@Entity
public class HumidityMeasure extends Measure{

	private HumidityMeasure() {
	}
	
	private HumidityMeasure(LocalDateTime measureTime, Double value, Request request, Station station) {
		super(measureTime, value, request, station);
	}
	
	public static HumidityMeasure of(LocalDateTime measureTime, Double umidity, Request request, Station station) {
		return new HumidityMeasure(measureTime, umidity, request, station);
	}
}
