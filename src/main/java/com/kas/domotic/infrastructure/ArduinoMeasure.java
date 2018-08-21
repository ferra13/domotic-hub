package com.kas.domotic.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kas.domotic.domain.remote.Request;
import com.kas.domotic.domain.remote.RequestType;
import com.kas.domotic.domain.repository.RequestRepository;

@Component
public class ArduinoMeasure {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoMeasure.class);
	
	private static final String ARDUINO_URL = "http://93.50.56.34:50";
	
	@Autowired
	private RequestRepository reqRepository;
	
	
	
	protected ArduinoMeasure() throws IOException {
		
	}

	private HttpURLConnection getConnection() throws IOException {
		URL arduinoUrl = new URL(ARDUINO_URL);
		HttpURLConnection con = (HttpURLConnection) arduinoUrl.openConnection();
		con.setRequestMethod("GET");
		con.setReadTimeout(30*1000);
		return con;
	}
	
	@Transactional
	public ArduinoRequest readMeasure() {
		HttpURLConnection connection = null;
		try {
			 Request req = Request.newRequest(RequestType.REMOTE);
			 connection = getConnection();
			LOGGER.info("get data from arduino");
			int response = connection.getResponseCode();
			if(response == 200){
				LOGGER.info("request success");
				BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				req.response();
				LOGGER.info("duration: " + req.duration() + " milliseconds");
				req = reqRepository.save(req);
				LOGGER.info(content.toString());
				return ArduinoRequest.of(req, content.toString());
			}
			else {
				LOGGER.error("Problem with request measure to Arduino. Response code: " + response);
				return null;
			}
		} catch (SocketTimeoutException e) {
			LOGGER.error("Timout received while request to Arduino", e);
			return null;
		} catch (IOException e) {
			LOGGER.error("Error request data to Arduino", e);
			return null;
		} finally {
			connection.disconnect();
		}
		
	}
}
