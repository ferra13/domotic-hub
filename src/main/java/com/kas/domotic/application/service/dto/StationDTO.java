package com.kas.domotic.application.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StationDTO {

	private final String id;
	private final String name;
	private final String macAddress;
	private final Double latitude;
	private final Double longitude;
	private final String url;
	
	@JsonCreator
	private StationDTO(@JsonProperty("id") String id, @JsonProperty("name") String name, 
			@JsonProperty("macAddress") String macAddress, @JsonProperty("latitude") Double latitude, 
			@JsonProperty("longitude") Double longitude,
			@JsonProperty("url") String url) {
		this.id = id;
		this.name = name;
		this.macAddress = macAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.url = url;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	 
	public String getMacAddress() {
		return macAddress;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public String getUrl() {
		return url;
	}
	
	public static StationDTO of(String id, String name, String macAddress, Double latitude, Double longitude, String url) {
		return new StationDTO(id, name, macAddress, latitude, longitude, url);
	}
}
