package com.kas.domotic.domain.repository;

import org.springframework.data.jpa.domain.Specification;

import com.kas.domotic.domain.measure.Position;
import com.kas.domotic.domain.measure.TemperatureMeasure;
import com.kas.domotic.domain.measure.TemperatureMeasure_;

public class MeasureSpecification {

	private MeasureSpecification() {
		
	}
	
	public static Specification<TemperatureMeasure> withPosition(final Position position) {
		return (root, query, cb) -> {
			return cb.equal(root.get(TemperatureMeasure_.position), position);
		};
	}
}
