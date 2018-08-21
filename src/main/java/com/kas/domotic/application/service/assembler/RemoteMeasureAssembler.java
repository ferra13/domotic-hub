package com.kas.domotic.application.service.assembler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kas.domotic.application.service.dto.RemoteMeasureDTO;
import com.kas.domotic.domain.Measure;
import com.kas.domotic.domain.measure.HumidityMeasure;
import com.kas.domotic.domain.measure.LightMeasure;
import com.kas.domotic.domain.measure.Position;
import com.kas.domotic.domain.measure.PressureMeasure;
import com.kas.domotic.domain.measure.TemperatureMeasure;
import com.kas.domotic.domain.remote.Request;
import com.kas.domotic.domain.station.Station;

public class RemoteMeasureAssembler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteMeasureAssembler.class);
	// Example:
	// <!DOCTYPE HTML><html><meta http-equiv="refresh" content="5">
	// <title>Stazione meteo</title><center><h1>STAZIONE METEO</h1><h2>dati da interno casa</h2>
	// <h4>Temperatura : 24.87<sup>0</sup>C<br />Pressione : 1009.60hPa<br />Umidità : 42.00%</h4></center></html>
//	public static ImmutableList<Measure> fromArduino(ArduinoRequest request) {
//		LocalDateTime now = LocalDateTime.now();
//		ImmutableList.Builder<Measure> result = ImmutableList.builder();
//
//		String pressure = request.content().substring(
//				request.content().indexOf("Pressione : ") + "Pressione : ".length(), request.content().indexOf("hPa"));
//		LOGGER.info("pressure:" + pressure.trim());
//		result.add(PressureMeasure.of(now, Double.parseDouble(pressure.trim()), request.request()));
//
//		String umidity = request.content().substring(request.content().indexOf("Umidità : ") + "Umidità : ".length(),
//				request.content().indexOf("%"));
//		LOGGER.info("umidity:" + umidity.trim());
//		result.add(HumidityMeasure.of(now, Double.parseDouble(umidity.trim()), request.request()));
//
//		String temperature = request.content().substring(
//				request.content().indexOf("Temperatura : ") + "Temperatura : ".length(),
//				request.content().indexOf("<sup>0</sup>"));
//		LOGGER.info("temperatureint:" + temperature.trim());
//		result.add(
//				TemperatureMeasure.of(now, Double.parseDouble(temperature.trim()), Position.INSIDE, request.request()));
//
//		return result.build();
//	}
	
	public static Measure fromDto(RemoteMeasureDTO dto, LocalDateTime now, Request request, Station station) {
		switch (dto.getType()) {
		case TEMPERATUREINT:
			return TemperatureMeasure.of(now, dto.getValue(), Position.INSIDE, request, station);
		case TEMPERATUREEST:
			return TemperatureMeasure.of(now, dto.getValue(), Position.OUTSIDE, request, station);
		case HUMIDITY:
			return HumidityMeasure.of(now, dto.getValue(), request, station);
		case PRESSURE:
			return PressureMeasure.of(now, dto.getValue(), request, station);
		case LIGHT:
			return LightMeasure.of(now, dto.getValue(), request, station);
		default:
			LOGGER.error("Unknow measure " + dto.getType(), request);
			return null;
		}
	}
}
