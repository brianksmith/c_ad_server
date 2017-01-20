package org.brian.smith.adserver.application.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.brian.smith.adserver.domain.Ad;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdServerIntegrationTests {

    @LocalServerPort
    private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test() throws Exception {
		// Test error case 
		Ad ad = new Ad();
		ResponseEntity<Ad> entity = restTemplate.postForEntity("http://localhost:" + port + "/ad", ad, Ad.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		// Test Create
		ad.setPartner_id("1");
		ad.setDuration(5);
		ad.setAd_content("test content");
		
		entity = restTemplate.postForEntity("http://localhost:" + port + "/ad", ad, Ad.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		// Test Add Same Object
		entity = restTemplate.postForEntity("http://localhost:" + port + "/ad", ad, Ad.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		// Test Get
		entity = restTemplate.getForEntity("http://localhost:" + port + "/ad/1", Ad.class);
		assertThat(entity.getBody()).isEqualToComparingOnlyGivenFields(ad, "partner_id", "ad_content", "duration");
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		Thread.sleep(6000);
		
		// Test Get
		entity = restTemplate.getForEntity("http://localhost:" + port + "/ad/1", Ad.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		// Test Get All .. this does not filter out expired ad campaigns
		ResponseEntity<Collection> entities = restTemplate.getForEntity("http://localhost:" + port + "/ads", Collection.class);
		assertThat(entities.getBody().size()).isEqualTo(1);
		assertThat(entities.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
