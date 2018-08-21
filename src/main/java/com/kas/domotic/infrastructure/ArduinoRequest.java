package com.kas.domotic.infrastructure;

import com.kas.domotic.domain.remote.Request;

public class ArduinoRequest {

	private Request request;
	private String content;
	
	private ArduinoRequest(Request request, String content) {
		this.request = request;
		this.content = content;
	}
	
	public Request request() {
		return request;
	}
	
	public String content() {
		return content;
	}
	
	public static ArduinoRequest of(Request request, String content) {
		return new ArduinoRequest(request, content);
	}
}
