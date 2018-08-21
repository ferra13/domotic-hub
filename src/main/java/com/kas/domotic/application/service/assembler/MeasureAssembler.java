package com.kas.domotic.application.service.assembler;

import java.time.ZoneId;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.application.service.dto.TemperatureDTO;
import com.kas.domotic.domain.Measure;
import com.kas.domotic.domain.measure.HumidityMeasure;
import com.kas.domotic.domain.measure.LightMeasure;
import com.kas.domotic.domain.measure.MeasureType;
import com.kas.domotic.domain.measure.PressureMeasure;
import com.kas.domotic.domain.measure.TemperatureMeasure;

public class MeasureAssembler {

	private static TemperatureDTO fromTemperature(TemperatureMeasure temperature) {
		return TemperatureDTO.of(temperature.id(), temperature.value(),
				temperature.measureTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
				MeasureType.TEMPERATURE, temperature.position());
	}

	private static MeasureDTO fromUmidity(HumidityMeasure umidity) {
		return MeasureDTO.of(umidity.id(), umidity.value(),
				umidity.measureTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), MeasureType.HUMIDITY);
	}

	private static MeasureDTO fromPressure(PressureMeasure pressure) {
		return MeasureDTO.of(pressure.id(), pressure.value(),
				pressure.measureTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), MeasureType.PRESSURE);
	}
	
	private static MeasureDTO fromLight(LightMeasure light) {
		return MeasureDTO.of(light.id(), light.value(),
				light.measureTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), MeasureType.LIGHT);
	}

	public static MeasureDTO fromMeasure(Measure measure) {
		if (measure instanceof TemperatureMeasure) {
			return fromTemperature((TemperatureMeasure) measure);
		} else if (measure instanceof PressureMeasure) {
			return fromPressure((PressureMeasure) measure);
		} else if (measure instanceof HumidityMeasure) {
			return fromUmidity((HumidityMeasure) measure);
		} else if (measure instanceof LightMeasure) {
			return fromLight((LightMeasure) measure);
		}
		
		return null;
	}

	public static ImmutableList<MeasureDTO> fromMeasureList(ImmutableList<Measure> result) {
		return ImmutableList.copyOf(result.stream().map(measure -> fromMeasure(measure)).collect(Collectors.toList()));

	}

}
