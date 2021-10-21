package com.kas.domotic.application.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.assembler.StationAssembler;
import com.kas.domotic.application.service.dto.StationDTO;
import com.kas.domotic.domain.repository.StationRepository;
import com.kas.domotic.domain.station.Station;

@Service
public class StationServiceImpl implements StationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StationServiceImpl.class);
	
	 @Autowired
	 private StationRepository stationRepository;
	
	@Override
	public Station registerStation(StationDTO dto) {
		Station stationFound;
		if(dto.getLatitude() != null && dto.getLongitude() != null) {
			stationFound = stationRepository.findByNameAndLatitudeAndLongitude(dto.getName(), dto.getLatitude(), dto.getLongitude());
		} else {
			stationFound = stationRepository.findByName(dto.getName());
		}
		if(stationFound == null) {
			LOGGER.info("register new station for " + dto.getName());
			return stationRepository.save(Station.register(dto.getName(), dto.getMacAddress(), dto.getLatitude(),
					dto.getLongitude(), dto.getUrl()));
		}
		else {
			return stationFound;
		}
	}

	@Override
	public StationDTO get(String stationId) {
		Optional<Station> station = stationRepository.findById(stationId);
		return station.map(StationAssembler::fromStation).orElse(null);
	}

	@Override
	public List<StationDTO> getAll() {
		ImmutableList.Builder<StationDTO> sBuilder = ImmutableList.builder();
		stationRepository.findAll().forEach(s -> sBuilder.add(StationAssembler.fromStation(s)));
		return sBuilder.build();
		
	}

}
