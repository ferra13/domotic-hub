package com.kas.domotic.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.google.common.base.Strings;
import com.kas.domotic.domain.remote.Request;
import com.kas.domotic.domain.station.Station;

@MappedSuperclass
public abstract class Measure {
	
	@Id
	private String id;
	
	@Column
	private LocalDateTime measureTime;
	
	@Column
	private Double value;
	
	@ManyToOne
	private Request request;
	
	@ManyToOne
	private Station station;
	
	protected Measure() {
	}
	
	protected Measure(String id) {
		this();
		if(Strings.isNullOrEmpty(id)) {
			throw new IllegalArgumentException("invalid entity identifier");
		}
		this.id = id;
	}
	
	protected Measure(LocalDateTime measureTime, Double value, Request request, Station station) {
		this(UUID.randomUUID().toString());
		this.measureTime = measureTime;
		this.value = value;
		this.request = request;
		this.station = station;
	}
	
	public String id() {
		return id;
	}
	
	public LocalDateTime measureTime() {
		return measureTime;
	}
	
	public Double value() {
		return value;
	}
	
	public Request request() {
		return request;
	}
}
