package com.kas.domotic.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.MeasureService;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.domain.measure.Position;

@RestController
@RequestMapping("/api/measure")
public class MeasureController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MeasureController.class);

	@Autowired
	private MeasureService measureService;

	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	 public ImmutableList<MeasureDTO> measures() {
		return measureService.getAllMeasure();
	}
	
	@RequestMapping(value="/temperature", method = RequestMethod.GET)
	@CrossOrigin
	 public ImmutableList<MeasureDTO> temperatureMeasures(@RequestParam("position") String position) {
		return measureService.getAllTemperature(Position.valueOf(position));
	}
	
	@RequestMapping(value="/humidity", method = RequestMethod.GET)
	@CrossOrigin
	 public ImmutableList<MeasureDTO> humidityMeasures() {
		return measureService.getAllUmidity();
	}
	
	@RequestMapping(value="/pressure", method = RequestMethod.GET)
	@CrossOrigin
	 public ImmutableList<MeasureDTO> pressureMeasures() {
		return measureService.getAllPressure();
	}
	@RequestMapping(value="/light", method = RequestMethod.GET)
	@CrossOrigin
	 public ImmutableList<MeasureDTO> lightMeasures() {
		return measureService.getAllLight();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public MeasureDTO getMeasure(@PathVariable String id) {
		return measureService.getMeasure(id);
	}
}
