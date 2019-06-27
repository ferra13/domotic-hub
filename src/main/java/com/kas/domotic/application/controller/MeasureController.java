package com.kas.domotic.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kas.domotic.application.service.MeasureService;
import com.kas.domotic.application.service.PageParams;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.application.service.dto.PageDTO;
import com.kas.domotic.domain.measure.Position;

@RestController
@RequestMapping("/api/measure")
public class MeasureController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MeasureController.class);

	@Autowired
	private MeasureService measureService;

//	@RequestMapping(method = RequestMethod.GET)
//	@CrossOrigin
//	 public ImmutableList<MeasureDTO> measures() {
//		return measureService.getAllMeasure();
//	}
	
	@RequestMapping(value="/temperature", method = RequestMethod.GET)
	@CrossOrigin
	 public PageDTO<MeasureDTO> temperatureMeasures(@RequestParam("position") String position,
			 @RequestParam(name="page", defaultValue="0") int page,
			 @RequestParam(name="size", defaultValue="10") int size,
			 @RequestParam(name="order", required=false, defaultValue="measureTime") String columnName,
			 @RequestParam(name="direction", required=false, defaultValue="ASC") Direction direction) {
		return measureService.getAllTemperature(Position.valueOf(position), PageParams.of(page, size, direction, columnName));
	}
	
	@RequestMapping(value="/humidity", method = RequestMethod.GET)
	@CrossOrigin
	 public PageDTO<MeasureDTO> humidityMeasures(@RequestParam(name="page", defaultValue="0") int page,
			 @RequestParam(name="size", defaultValue="10") int size,
			 @RequestParam(name="order", required=false, defaultValue="measureTime") String columnName,
			 @RequestParam(name="direction", required=false, defaultValue="ASC") Direction direction) {
		return measureService.getAllUmidity(PageParams.of(page, size, direction, columnName));
	}
	
	@RequestMapping(value="/pressure", method = RequestMethod.GET)
	@CrossOrigin
	 public PageDTO<MeasureDTO> pressureMeasures(@RequestParam(name="page", defaultValue="0") int page,
			 @RequestParam(name="size", defaultValue="10") int size,
			 @RequestParam(name="order", required=false, defaultValue="measureTime") String columnName,
			 @RequestParam(name="direction", required=false, defaultValue="ASC") Direction direction) {
		return measureService.getAllPressure(PageParams.of(page, size, direction, columnName));
	}
	@RequestMapping(value="/light", method = RequestMethod.GET)
	@CrossOrigin
	 public PageDTO<MeasureDTO> lightMeasures(@RequestParam(name="page", defaultValue="0") int page,
			 @RequestParam(name="size", defaultValue="10") int size,
			 @RequestParam(name="order", required=false, defaultValue="measureTime") String columnName,
			 @RequestParam(name="direction", required=false, defaultValue="ASC") Direction direction) {
		return measureService.getAllLight(PageParams.of(page, size, direction, columnName));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public MeasureDTO getMeasure(@PathVariable String id) {
		return measureService.getMeasure(id);
	}
}
