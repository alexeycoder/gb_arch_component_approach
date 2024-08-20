package edu.alexey.ticketstore.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.alexey.ticketstore.entities.Customer;
import edu.alexey.ticketstore.repositories.abstractions.CustomerRepository;

/**
 * Демонстрационный репозиторий, имитирующий работу с БД покупателей
 */
public class DemoCustomerRepository implements CustomerRepository {

	private static final ArrayList<Customer> CUSTOMERS;

	static {
		CUSTOMERS = new ArrayList<Customer>(
				List.of(
						new Customer(1, "Ivan", "1111", 2),
						new Customer(2, "Vasiliy", "2222", 3),
						new Customer(3, "Fedor", "3333", 4),
						new Customer(4, "Grigoriy", "4444", 5)));
	}

	@Override
	public int create(String name, String password, long cardNumber) {

		int id = CUSTOMERS.size() + 1;
		Customer newCustomer = new Customer(id, name, password, cardNumber);
		for (var customer : CUSTOMERS) {
			if (customer.getCustomerId() == newCustomer.getCustomerId()) {
				throw new RuntimeException("A customer already exists");
			}
		}

		CUSTOMERS.add(newCustomer);
		return newCustomer.getCustomerId();
	}

	@Override
	public Customer read(int customerId) {

		for (var customer : CUSTOMERS) {
			if (customer.getCustomerId() == customerId) {
				return customer;
			}
		}
		throw new RuntimeException("A customer with this ID not found");
	}

	@Override
	public Customer findByName(String customerName) {

		for (var customer : CUSTOMERS) {
			if (Objects.equals(customerName, customer.getName())) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public List<Customer> readAll() {

		if (CUSTOMERS == null) {
			throw new RuntimeException("Customers database isn't ready");
		}

		return CUSTOMERS;
	}

	@Override
	public boolean update(Customer customer) {

		for (Customer aCustomer : CUSTOMERS) {
			if (aCustomer.getCustomerId() == customer.getCustomerId()) {
				CUSTOMERS.remove(aCustomer);
				CUSTOMERS.add(customer);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Customer customer) {
		for (Customer aCustomer : CUSTOMERS) {
			if (aCustomer.equals(customer)) {
				CUSTOMERS.remove(aCustomer);
				return true;
			}
		}
		return false;
	}

}
