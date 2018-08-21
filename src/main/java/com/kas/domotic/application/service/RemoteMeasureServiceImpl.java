package com.kas.domotic.application.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.assembler.MeasureAssembler;
import com.kas.domotic.application.service.assembler.RemoteMeasureAssembler;
import com.kas.domotic.application.service.assembler.StationAssembler;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.application.service.dto.RemoteMeasureDTO;
import com.kas.domotic.application.service.dto.StationDTO;
import com.kas.domotic.domain.Measure;
import com.kas.domotic.domain.measure.HumidityMeasure;
import com.kas.domotic.domain.measure.LightMeasure;
import com.kas.domotic.domain.measure.PressureMeasure;
import com.kas.domotic.domain.measure.TemperatureMeasure;
import com.kas.domotic.domain.repository.HumidityRepository;
import com.kas.domotic.domain.repository.LightRepository;
import com.kas.domotic.domain.repository.PressureRepository;
import com.kas.domotic.domain.repository.StationRepository;
import com.kas.domotic.domain.repository.TemperatureRepository;
import com.kas.domotic.domain.station.Station;
import com.kas.domotic.infrastructure.ArduinoMeasure;
import com.kas.domotic.infrastructure.ArduinoRequest;

@Service
public class RemoteMeasureServiceImpl implements RemoteMeasureService {

	@Autowired
	ArduinoMeasure arduino;

	@Autowired
	private TemperatureRepository tRepo;

	@Autowired
	private PressureRepository pRepo;

	@Autowired
	private HumidityRepository uRepo;
	
	@Autowired
	private LightRepository lRepo;
	
	@Autowired
	private StationRepository stationRepo;

	private final static Logger LOGGER = LoggerFactory.getLogger(RemoteMeasureServiceImpl.class);

	protected RemoteMeasureServiceImpl() {

	}

	@Override
	@Transactional
	public Boolean createMeasure(String measures) {
		System.out.println(measures);
		return true;
	}

	@Override
	@Transactional
	public String getMeasure(StationDTO station) {
		ArduinoRequest measures = arduino.readMeasure();
		if (measures != null) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = null;
			try {
				node = mapper.readTree(measures.content());
				final LocalDateTime now = LocalDateTime.now();
				
					if (node != null) {
					node.forEach(jmeasure -> {
						RemoteMeasureDTO rDto;
						try {
							rDto = mapper.treeToValue(jmeasure, RemoteMeasureDTO.class);
							Measure measure = RemoteMeasureAssembler.fromDto(rDto, now, measures.request(), StationAssembler.fromDTO(station)); 
							if (measure instanceof TemperatureMeasure) {
								tRepo.save((TemperatureMeasure) measure);
							} else if (measure instanceof PressureMeasure) {
								pRepo.save((PressureMeasure) measure);
							} else if (measure instanceof HumidityMeasure) {
								uRepo.save((HumidityMeasure) measure);
							} else if (measure instanceof LightMeasure) {
								lRepo.save((LightMeasure) measure);
							} else {
								LOGGER.error("Unknown type of measure");
							}
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
				}
			} catch (JsonProcessingException e) {
				LOGGER.error("Error processing Json String: " + measures, e);
			} catch (IOException e) {
				LOGGER.error("Error during parsing Json String", e);
			}
			return "ok";
		} else {
			return "ko";
		}
	}

	public static RemoteMeasureService build() {
		return new RemoteMeasureServiceImpl();
	}

	@Override
	@Transactional(readOnly=true)
	public ImmutableList<MeasureDTO> getCurrentMeasure(String stationId) {
		Station currentStation = stationRepo.findOne(stationId);
		ArduinoRequest measures = arduino.readMeasure();
		if(measures != null) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = null;
			ImmutableList.Builder<MeasureDTO> currentMeasures = ImmutableList.builder();
			try {
				LocalDateTime now = LocalDateTime.now();
				node = mapper.readTree(measures.content());
				node.forEach(m -> {
					try {
						RemoteMeasureDTO dto = mapper.treeToValue(m, RemoteMeasureDTO.class);
						currentMeasures.add(MeasureAssembler.fromMeasure(RemoteMeasureAssembler.fromDto(dto, now, measures.request(), currentStation)));
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				return currentMeasures.build();
			} catch (JsonProcessingException e) {
				LOGGER.error("Error processing Json String: " + measures, e);
			} catch (IOException e) {
				LOGGER.error("Error during parsing Json String", e);
			}
		}
		
		return null;
	}
}
