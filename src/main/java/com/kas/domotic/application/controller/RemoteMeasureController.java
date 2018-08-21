package com.kas.domotic.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.RemoteMeasureService;
import com.kas.domotic.application.service.StationService;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.application.service.dto.StationDTO;

@RestController
@RequestMapping("remote/measure")
public class RemoteMeasureController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteMeasureController.class);
	 @Autowired
	 private RemoteMeasureService remoteMeasureService;
	 @Autowired
	 private StationService stationService;
	
//	@Scheduled(fixedRate = 60*60*1000)
	public String getAllMeasure() {
		LOGGER.info("Get measure from Arduino");
		List<StationDTO> stations = stationService.getAll();
		stations.forEach(s -> {
			remoteMeasureService.getMeasure(s);
		});
		
		return "Ok";
	}
 
	@RequestMapping(value ="create", method= RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public int createMeasures(@RequestBody String measures) {
		LOGGER.info("incoming request: ");
		LOGGER.info(measures);
		if(remoteMeasureService.createMeasure(measures)) {
			return HttpStatus.CREATED.value();
		} else {
			return HttpStatus.INTERNAL_SERVER_ERROR.value();
		}
	}
	
	@RequestMapping(value="getCurrent", method=RequestMethod.GET)
	@CrossOrigin
	public ImmutableList<? extends MeasureDTO> getCurrentMeasure(@RequestParam String stationId) {
		
		ImmutableList<? extends MeasureDTO> result = remoteMeasureService.getCurrentMeasure(stationId);
		return result;
	}
	
}
