package com.kas.domotic.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;
import com.kas.domotic.application.service.assembler.MeasureAssembler;
import com.kas.domotic.application.service.dto.MeasureDTO;
import com.kas.domotic.domain.measure.Position;
import com.kas.domotic.domain.repository.HumidityRepository;
import com.kas.domotic.domain.repository.LightRepository;
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
	public ImmutableList<MeasureDTO> getAllMeasure() {
		ImmutableList.Builder<MeasureDTO> result = ImmutableList.builder();
		tRepo.findAll().forEach(t -> result.add(MeasureAssembler.fromMeasure(t)));
		uRepo.findAll().forEach(u -> result.add(MeasureAssembler.fromMeasure(u)));
		pRepo.findAll().forEach(p -> result.add(MeasureAssembler.fromMeasure(p)));
		return result.build();
	}
	
	@Override
	@Transactional(readOnly=true)
	public MeasureDTO getMeasure(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public ImmutableList<MeasureDTO> getAllTemperature() {
		ImmutableList.Builder<MeasureDTO> result = ImmutableList.builder();
		tRepo.findAll(new Sort(Direction.ASC, "measureTime")).forEach(t -> result.add(MeasureAssembler.fromMeasure(t)));
		return result.build();
	}
	
	@Override
	@Transactional(readOnly=true)
	public ImmutableList<MeasureDTO> getAllTemperature(Position position) {
		ImmutableList.Builder<MeasureDTO> result = ImmutableList.builder();
		tRepo.findByPositionOrderByMeasureTimeAsc(position).forEach(t -> result.add(MeasureAssembler.fromMeasure(t)));
		return result.build();
	}
	

	@Override
	@Transactional(readOnly=true)
	public ImmutableList<MeasureDTO> getAllUmidity() {
		ImmutableList.Builder<MeasureDTO> result = ImmutableList.builder();
		uRepo.findAll(new Sort(Direction.ASC, "measureTime")).forEach(u -> result.add(MeasureAssembler.fromMeasure(u)));
		return result.build();
	}

	@Override
	@Transactional(readOnly=true)
	public ImmutableList<MeasureDTO> getAllPressure() {
		ImmutableList.Builder<MeasureDTO> result = ImmutableList.builder();
		pRepo.findAll(new Sort(Direction.ASC, "measureTime")).forEach(p -> result.add(MeasureAssembler.fromMeasure(p)));
		return result.build();
	}

	@Override
	@Transactional(readOnly=true)
	public ImmutableList<MeasureDTO> getAllLight() {
		ImmutableList.Builder<MeasureDTO> result = ImmutableList.builder();
		lRepo.findAll(new Sort(Direction.ASC, "measureTime")).forEach(l -> result.add(MeasureAssembler.fromMeasure(l)));
		return result.build();
	}

}
