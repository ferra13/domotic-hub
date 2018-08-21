package com.kas.domotic.domain.repository;

import java.util.List;

import com.kas.domotic.domain.measure.Position;
import com.kas.domotic.domain.measure.TemperatureMeasure;

public interface TemperatureRepository extends MeasureRepository<TemperatureMeasure> {

	List<TemperatureMeasure> findByPositionOrderByMeasureTimeAsc(Position position);
}
