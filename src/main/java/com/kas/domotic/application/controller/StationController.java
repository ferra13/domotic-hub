package com.kas.domotic.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kas.domotic.application.service.StationService;
import com.kas.domotic.application.service.dto.StationDTO;
import com.kas.domotic.domain.station.Station;

@RestController
@RequestMapping("station")
public class StationController {
	
	@Autowired
	 private StationService stationService;
	
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	@CrossOrigin 
	public String createStation(@RequestBody StationDTO dto) {
		Station registeredStation = stationService.registerStation(dto);
		return registeredStation.id();
	}
	
	@CrossOrigin 
	public List<StationDTO> getStations(@RequestBody StationDTO dto) {
		
		return stationService.getAll();
	}
}
