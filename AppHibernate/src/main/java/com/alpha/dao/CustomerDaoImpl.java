package com.alpha.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.entities.customer.Customer;

@Transactional
@Repository("customerDaol")
public class CustomerDaoImpl implements CustomerDao {

	// private fields
	private static Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);
	private SessionFactory sessionFactory;

	// constructors
	public CustomerDaoImpl() {

	}

	@Resource(name = "sessionFactory")
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// CRUD methods
	// return all customers
	@Transactional(readOnly = true)
	@Override
	public List<Customer> findAll() {

		return sessionFactory.getCurrentSession().createQuery("from Customer").list();
	}

	// return customer by Id input
	@Transactional(readOnly = true)
	@Override
	public Customer findCustomer(int customerId) {

		Customer customer = (Customer) sessionFactory.getCurrentSession().getNamedQuery("Customer_ById")
				.setParameter("customer_id", customerId).uniqueResult();
		
		return customer;
	}

	// insert customer object into DB
	@Override
	public void insert(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
		logger.info("Customer added successfully with id " + customer.getId());
	}

	// it removes customer from DB by id input
	@Override
	public void delete(int id) {
		Customer customer = findCustomer(id);
		sessionFactory.getCurrentSession().delete(customer);
		logger.info("Customer with id " + id + "deleted successfully");
	}

	// it updates customer data
	@Override
	public void update(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
		logger.info("Customer updated successfully");
	}

}
