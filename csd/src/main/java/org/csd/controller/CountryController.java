package org.csd.controller;

import java.util.List;

import org.csd.model.Country;
import org.csd.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	// Add Country
	@RequestMapping(value = "/country/new", method = RequestMethod.POST)
	public ResponseEntity<Void> addCountry(@RequestBody Country country, UriComponentsBuilder ucb) {
		if (countryService.isCountryExist(country)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			countryService.saveCountry(country);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/country/{id}").buildAndExpand(country.getId()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}

	// Get Single Country
	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> getCountry(@PathVariable("id") int id) {
		Country country = countryService.findById(id);
		if (country == null) {
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}

	// Get All Country
	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public ResponseEntity<List<Country>> listAllCountry() {
		List<Country> country = countryService.findAllCountry();
		if (country.isEmpty()) {
			return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Country>>(country, HttpStatus.OK);
	}

	// Update Country
	@RequestMapping(value = "/country/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Country> updateCountry(@PathVariable("id") long id, @RequestBody Country cus) {
		Country country = countryService.findById(id);
		if (country == null) {
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
		country.setCountryName(cus.getCountryName());
		country.setPopulation(cus.getPopulation());

		countryService.updateCountry(country);
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}

	// Delete Country
	@RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Country> deleteCountry(@PathVariable("id") long id) {
		Country country = countryService.findById(id);
		if (country == null) {
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
		countryService.deleteCountryById(id);
		return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
	}

	// Delete All Country
	@RequestMapping(value = "/country/deleteAll", method = RequestMethod.DELETE)
	public ResponseEntity<Country> deleteAll() {
		countryService.deleteAllCountry();
		return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
	}
}
