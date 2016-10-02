package org.csd.dao;

import java.util.List;

import org.csd.model.Country;

public interface CountryDAO {
	Country findById(long id);
	Country findByName(String name);
	void saveCountry(Country country);
	void updateCountry(Country country);
	void deleteCountryById(long id);
	List<Country> findAllCountry();
	void deleteAllCountry();
	boolean isCountryExist(Country country);
}
