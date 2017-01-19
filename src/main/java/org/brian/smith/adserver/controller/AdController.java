package org.brian.smith.adserver.controller;

import org.brian.smith.adserver.domain.Ad;
import org.brian.smith.adserver.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdController {

	@Autowired
	private AdRepository adRepository;
	
	@RequestMapping(value="/ad", method = RequestMethod.POST)
	public ResponseEntity<Ad> createGreeting(@RequestBody Ad ad) {
		return new ResponseEntity<Ad>(adRepository.create(ad), HttpStatus.CREATED);
	}

//	@RequestMapping(value="/ad", method = RequestMethod.GET)
//	public Ad getGreetings(@RequestParam(value="name", defaultValue="World") String name) {
//		return new ResponseEntity<Ad>(adDao.getAd(partnerId))
//	}
	
	@RequestMapping(value="/ad/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ad> getGreeting(@PathVariable String id) {
		return new ResponseEntity<Ad>(adRepository.getAd(id), HttpStatus.OK);
	}
	
}
