package edu.alexey.ticketstore.clientapp;

import edu.alexey.ticketstore.exceptions.AlreadyExistingCustomerException;
import edu.alexey.ticketstore.exceptions.AuthorizationException;

public interface StoreServiceFactory {

	StoreService forExistingCustomer(String loginName, String password) throws AuthorizationException;

	StoreService forNewCustomer(String loginName, String password, long cardNumber)
			throws AlreadyExistingCustomerException;
}
