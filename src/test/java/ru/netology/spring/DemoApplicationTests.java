package ru.netology.spring;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	private static final GenericContainer<?> myApp1 = new GenericContainer<>("app:v1")
			.withExposedPorts(8090);
	private static final GenericContainer<?> myApp2 = new GenericContainer<>("app:v2")
			.withExposedPorts(8091);
    private static final String PROFILE = "/profile";

	@BeforeAll
    static void setUp() {
    myApp1.start();
    myApp2.start();
    }

	@Test
	void contextLoads() {
		ResponseEntity<String> forEntity1 = restTemplate.getForEntity("http://localhost:" + myApp1.getMappedPort(8090) + PROFILE, String.class);
        Assert.assertEquals("Current profile is dev", forEntity1.getBody());
		ResponseEntity<String> forEntity2 = restTemplate.getForEntity("http://localhost:" + myApp2.getMappedPort(8091) + PROFILE, String.class);
        Assert.assertEquals("Current profile is production", forEntity2.getBody());
	}

}
