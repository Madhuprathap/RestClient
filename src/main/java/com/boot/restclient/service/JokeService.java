package com.boot.restclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.restclient.json.JokeResponse;

@Service
public class JokeService {
	private Logger logger = LoggerFactory.getLogger(JokeService.class);
	
	private static final String BASE= "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
	private RestTemplate template;
	//No need to provide autowire when we have only one constructor
	public JokeService(RestTemplateBuilder builder) {
		template = builder.build();
	}
	
	public String getJoke(String first, String last) {
		String url = String.format("%s&firsName=%s&lastName=%s", BASE, first, last);
		JokeResponse response = template.getForObject(url, JokeResponse.class);
		logger.info(response.getValue().getJoke());
		return response.getValue().getJoke();
	}
}
