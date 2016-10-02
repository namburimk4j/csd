package org.csd.service;

import java.util.List;

import org.csd.model.Country;

public interface CountryService {
	Country findById(long id);
	Country findByName(String name);
	void saveCountry(Country country);
	void updateCountry(Country country);
	void deleteCountryById(long id);
	List<Country> findAllCountry();
	void deleteAllCountry();
	boolean isCountryExist(Country country);
}
