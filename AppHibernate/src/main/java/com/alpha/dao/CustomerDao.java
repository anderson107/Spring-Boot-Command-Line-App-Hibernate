package com.alpha.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.alpha.entities.customer.Customer;

// interface for customer CRUD operations
public interface CustomerDao {

	public List<Customer>findAll();
	public Customer findCustomer(int id);
	public void insert(Customer customer);
	public void delete(int id);
	public void update(Customer customer);
    public void setSessionFactory(SessionFactory session);
}
