package com.boot.restclient.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTest {
	
	@Autowired
	private JokeService service;
	
	@Test
	public void getJoke() {
		String joke = service.getJoke("madhu", "prathap");
		assertTrue(joke.contains("madhu") ||
				joke.contains("prathap"));
		
	}

}
