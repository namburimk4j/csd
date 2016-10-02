package org.csd.dao;

import java.util.ArrayList;
import java.util.List;

import org.csd.model.Country;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;


public class CountryDAOImpl implements CountryDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public Country findById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Country country = new Country();
		try {
			country = (Country)session.get(Country.class, id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}		
		return country;
	}
	
	public Country findByName(String name) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Country country = new Country();
		String hql = "from org.csd.model.Country where countryName = ?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, name);
			country = (Country)query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}		
		return country;
	}
	
	public void saveCountry(Country country) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if(country!=null){
			try {
				session.save(country);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}
		}
	}

	public void updateCountry(Country country) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if(country!=null){
			try {
				session.update(country);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}
		}
		
	}
	
	public void deleteCountryById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Country country = new Country();
		try {
			country = (Country)session.get(Country.class, id);
			session.delete(country);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")	
	public List<Country> findAllCountry() {
		List<Country> countrys = new ArrayList<Country>();
		Session session = sessionFactory.openSession();
		countrys = session.createQuery("from org.csd.model.Country").list();
		return countrys;
	}
	
	public void deleteAllCountry() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Country").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		
	}
	
	public boolean isCountryExist(Country country) {
		return findByName(country.getCountryName())!=null;
	}

	
}

