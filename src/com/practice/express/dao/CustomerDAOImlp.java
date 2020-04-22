package com.practice.express.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.express.entity.Customer;

@Repository
public class CustomerDAOImlp implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

		// execute query and get the result
		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		// save/update customer
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		Customer customer = session.get(Customer.class, theId);

		return customer;
	}

	@Override
	public void deleteCustomer(int id) {

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);

		query.executeUpdate();

	}

}
