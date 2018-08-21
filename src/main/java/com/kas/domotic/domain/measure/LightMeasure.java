package com.kas.domotic.domain.measure;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.kas.domotic.domain.Measure;
import com.kas.domotic.domain.remote.Request;
import com.kas.domotic.domain.station.Station;

@Entity
public class LightMeasure extends Measure {

	private LightMeasure() {
	}
	
	private LightMeasure(LocalDateTime measureTime, Double value, Request request, Station station) {
		super(measureTime, value, request, station);
	}
	
	public static LightMeasure of(LocalDateTime measureTime, Double light, Request request, Station station) {
		return new LightMeasure(measureTime, light, request, station);
	}
}
