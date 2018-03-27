package com.boot.restclient.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot.restclient.entities.Site;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeocoderServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(GeocoderServiceTest.class);
	
	@Autowired
	private GeocoderService service;
	
	@Test
	public void getLatLngWithoutStreet() throws Exception {
	    Site site = service.getLatLng("Boston", "MA");
	    assertEquals(42.36, site.getLatitude(), 0.01);
	    assertEquals(-71.06, site.getLongitude(), 0.01);
	}

	@Test
	public void getLatLngWithStreet() throws Exception {
	    Site site = service.getLatLng("1600 Ampitheatre Parkway",
	            "Mountain View", "CA");
	    logger.info(site.toString());
	    assertEquals(37.42, site.getLatitude(), 0.01);
	    assertEquals(-122.08, site.getLongitude(), 0.01);
	}
	
}
