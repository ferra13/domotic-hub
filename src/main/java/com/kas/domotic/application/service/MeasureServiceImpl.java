package com.kas.domotic.application.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.assembler.MeasureAssembler;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.application.service.dto.PageDTO;
import com.kas.domotic.domain.measure.HumidityMeasure;
import com.kas.domotic.domain.measure.LightMeasure;
import com.kas.domotic.domain.measure.Position;
import com.kas.domotic.domain.measure.PressureMeasure;
import com.kas.domotic.domain.measure.TemperatureMeasure;
import com.kas.domotic.domain.repository.HumidityRepository;
import com.kas.domotic.domain.repository.LightRepository;
import com.kas.domotic.domain.repository.MeasureSpecification;
import com.kas.domotic.domain.repository.PressureRepository;
import com.kas.domotic.domain.repository.TemperatureRepository;

@Service
public class MeasureServiceImpl implements MeasureService {

	@Autowired
	private TemperatureRepository tRepo;
	
	@Autowired 
	private HumidityRepository uRepo;
	
	@Autowired
	private PressureRepository pRepo;
	
	@Autowired
	private LightRepository lRepo;
	
	@Override
	@Transactional(readOnly=true)
	public MeasureDTO getMeasure(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public PageDTO<MeasureDTO> getAllTemperature(PageParams params) {
//		ImmutableList.Builder<MeasureDTO> result = ImmutableList.builder();
//		tRepo.findAll(new Sort(Direction.ASC, "measureTime")).forEach(t -> result.add(MeasureAssembler.fromMeasure(t)));
//		return result.build();
		Page<TemperatureMeasure> tempPage = tRepo.findAll(params.getPageRequest()); 
		if(tempPage != null) {
			ImmutableList<MeasureDTO> temperatures = ImmutableList.copyOf(
			tempPage.getContent().stream().map(temp -> MeasureAssembler.fromMeasure(temp)).collect(Collectors.toList()));
			return PageDTO.of(temperatures, params.page(), tempPage.getTotalElements());
		}
		return PageDTO.of(ImmutableList.of());
	}
	
	@Override
	@Transactional(readOnly=true)
	public PageDTO<MeasureDTO> getAllTemperature(Position position, PageParams params) {
		//ImmutableList.Builder<MeasureDTO> result = ImmutableList.builder();
		//tRepo.findByPositionOrderByMeasureTimeAsc(position).forEach(t -> result.add(MeasureAssembler.fromMeasure(t)));
		Page<TemperatureMeasure> tempPage = tRepo.findAll(MeasureSpecification.withPosition(position), params.getPageRequest());
		if(tempPage != null) {
			ImmutableList<MeasureDTO> temperatures = ImmutableList.copyOf(
					tempPage.getContent().stream().map(temp -> MeasureAssembler.fromMeasure(temp)).collect(Collectors.toList()));
					return PageDTO.of(temperatures, params.page(), tempPage.getTotalElements());
		}
		return PageDTO.of(ImmutableList.of());
	}
	

	@Override
	@Transactional(readOnly=true)
	public PageDTO<MeasureDTO> getAllUmidity(PageParams params) {
		Page<HumidityMeasure> humPage = uRepo.findAll(params.getPageRequest()); 
		if(humPage != null) {
			ImmutableList<MeasureDTO> humidities = ImmutableList.copyOf(
			humPage.getContent().stream().map(hum -> MeasureAssembler.fromMeasure(hum)).collect(Collectors.toList()));
			return PageDTO.of(humidities, params.page(), humPage.getTotalElements());
		}
		return PageDTO.of(ImmutableList.of());
	}

	@Override
	@Transactional(readOnly=true)
	public PageDTO<MeasureDTO> getAllPressure(PageParams params) {
		Page<PressureMeasure> pressPage = pRepo.findAll(params.getPageRequest()); 
		if(pressPage != null) {
			ImmutableList<MeasureDTO> pressures = ImmutableList.copyOf(
			pressPage.getContent().stream().map(press -> MeasureAssembler.fromMeasure(press)).collect(Collectors.toList()));
			return PageDTO.of(pressures, params.page(), pressPage.getTotalElements());
		}
		return PageDTO.of(ImmutableList.of());
	}

	@Override
	@Transactional(readOnly=true)
	public PageDTO<MeasureDTO> getAllLight(PageParams params) {
		Page<LightMeasure> lightPage = lRepo.findAll(params.getPageRequest()); 
		if(lightPage != null) {
			ImmutableList<MeasureDTO> lights = ImmutableList.copyOf(
			lightPage.getContent().stream().map(light -> MeasureAssembler.fromMeasure(light)).collect(Collectors.toList()));
			return PageDTO.of(lights, params.page(), lightPage.getTotalElements());
		}
		return PageDTO.of(ImmutableList.of());
	}
	

}
