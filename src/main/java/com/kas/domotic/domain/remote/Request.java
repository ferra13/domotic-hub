package com.kas.domotic.domain.remote;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.google.common.base.Strings;

@Entity
public class Request {

	@Id
	private String id;
	
	@Column
	private LocalDateTime requestTime;
	
	@Column
	private LocalDateTime responseTime;
	
	@Enumerated(value=EnumType.STRING)
	private RequestType type;
	
	private Request() {
	}
	
	private Request(String id) {
		this();
		if(Strings.isNullOrEmpty(id)) {
			throw new IllegalArgumentException("invalid entity identifier");
		}
		this.id = id;
	}
	
	private Request(RequestType type) {
		this(UUID.randomUUID().toString());
		this.requestTime = LocalDateTime.now();
		this.type = type;
	}
	
	public LocalDateTime requestTime() {
		return requestTime;
	}
	
	public LocalDateTime responseTime() {
		return responseTime;
	}
	
	public RequestType type() {
		return type;
	}
	
	public Long duration() {
		if(responseTime != null) {
			return Duration.between(requestTime, responseTime).toMillis();
		} else {
			return 0L;
		}
	}
	
	public static Request newRequest(RequestType type) {
		return new Request(type);
	}
	
	public void response() {
		this.responseTime = LocalDateTime.now();
	}
}
