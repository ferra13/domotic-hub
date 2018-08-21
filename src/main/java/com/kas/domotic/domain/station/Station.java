package com.kas.domotic.domain.station;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.common.base.Strings;

@Entity
public class Station {

	@Id
	private String id;
	
	private String name;
	
	private String macAddress;
	
	private Double latitude;
	
	private Double longitude;
	
	private String url;
	
	private Station() {
		
	}
	
	private Station(String id) {
		if(Strings.isNullOrEmpty(id)) {
			throw new IllegalArgumentException("Id must not be null");
		}
		this.id = id;
	}
	
	private Station(String name, String macAddress) {
		this(UUID.randomUUID().toString());
		this.name = name;
		this.macAddress = macAddress;
	}
	
	private Station(String name, String macAddress, Double latitude, Double longitude, String url) {
		this(name, macAddress);
		this.latitude = latitude;
		this.longitude = longitude;
		this.url = url;
	}
	
	
	public static Station register(String name, String macAddress) {
		return new Station(name, macAddress);
	}
	
	public static Station register(String name, String macAddress, Double latitude, Double longitude, String url) {
		return new Station(name, macAddress, latitude, longitude, url);
	}
	
	public String id() {
		return id;
	}
	
	public Double latitude() {
		return latitude;
	}
	
	public Double longitude() {
		return longitude;
	}
	
	public String name() {
		return name;
	}
	
	public String macAddress() {
		return macAddress;
	}
	
	public String url() {
		return url;
	}
}
