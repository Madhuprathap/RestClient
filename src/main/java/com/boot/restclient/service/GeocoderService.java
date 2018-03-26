package com.boot.restclient.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.restclient.entities.Site;
import com.boot.restclient.json.Response;

@Service
public class GeocoderService {
	private RestTemplate template;
	
	private static final String BASE = "https://maps.googleapis.com/maps/api/geocode/json";
	private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";
	
	public GeocoderService(RestTemplateBuilder builder) {
		template = builder.build();
	}
	
	private String encodeString(String s) {
	    try {
	        return URLEncoder.encode(s,"UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    return s;
	}

	public Site getLatLng(String... address) {
	    String encodedAddress = Stream.of(address)
	            .map(this::encodeString)
	            .collect(Collectors.joining(","));
	    String url = String.format("%s?address=%s&key=%s", BASE, encodedAddress, KEY);
	    Response response = template.getForObject(url, Response.class);
	    return new Site(response.getFormattedAddress(),
	            response.getLocation().getLat(),
	            response.getLocation().getLng());
	}
}
