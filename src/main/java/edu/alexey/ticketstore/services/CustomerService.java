package edu.alexey.ticketstore.services;

import java.util.List;
import java.util.Objects;

import edu.alexey.ticketstore.entities.Customer;
import edu.alexey.ticketstore.repositories.abstractions.CustomerRepository;

public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = Objects.requireNonNull(customerRepository);
	}

	public int create(String name, String password, long cardNumber) {
		return customerRepository.create(name, password, cardNumber);
	}

	public Customer read(int customerId) {
		return customerRepository.read(customerId);
	}

	public Customer findByName(String customerName) {
		return customerRepository.findByName(customerName);
	}

	public List<Customer> readAll() {
		return customerRepository.readAll();
	}

	public boolean update(Customer customer) {
		return customerRepository.update(customer);
	}

	public boolean delete(Customer customer) {
		return customerRepository.delete(customer);
	}

}
