package edu.alexey.ticketstore.services;

import edu.alexey.ticketstore.clientapp.StoreService;
import edu.alexey.ticketstore.clientapp.StoreServiceFactory;
import edu.alexey.ticketstore.entities.Customer;
import edu.alexey.ticketstore.exceptions.AlreadyExistingCustomerException;
import edu.alexey.ticketstore.exceptions.AuthorizationException;
import edu.alexey.ticketstore.repositories.DemoSingletoneDbContext;
import edu.alexey.ticketstore.repositories.abstractions.DbContext;

public class StoreServiceFactoryImpl implements StoreServiceFactory {

	private final DbContext context = DemoSingletoneDbContext.getInstance();

	@Override
	public StoreService forExistingCustomer(String loginName, String password) throws AuthorizationException {

		Customer customer = context.getCustomerRepository().findByLoginName(loginName);
		if (customer == null) {
			return null;
		}
		if (!customer.getPassword().equals(password)) {
			throw new AuthorizationException();
		}
		return new StoreServiceImpl(customer);
	}

	@Override
	public StoreService forNewCustomer(String loginName, String password, long cardNumber)
			throws AlreadyExistingCustomerException {

		Customer customer = context.getCustomerRepository().create(loginName, password, cardNumber);
		return new StoreServiceImpl(customer);
	}

}
