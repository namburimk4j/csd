package org.csd.service;

import java.util.List;

import org.csd.dao.CountryDAO;
import org.csd.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryDAO countryDao;	
	

	public void setCountryDao(CountryDAO countryDao) {
		this.countryDao = countryDao;
	}

	@Transactional
	public Country findById(long id) {		
		return countryDao.findById(id);
	}

	@Transactional
	public Country findByName(String name) {
		return countryDao.findByName(name);
	}

	@Transactional
	public void saveCountry(Country country) {
		countryDao.saveCountry(country);		
	}

	@Transactional
	public void updateCountry(Country country) {
		countryDao.updateCountry(country);
		
	}

	@Transactional
	public void deleteCountryById(long id) {
		countryDao.deleteCountryById(id);
		
	}

	@Transactional
	public List<Country> findAllCountry() {
		return countryDao.findAllCountry();
	}

	@Transactional
	public void deleteAllCountry() {
		countryDao.deleteAllCountry();
	}

	@Transactional
	public boolean isCountryExist(Country country) {
		return countryDao.isCountryExist(country);
	}
}

