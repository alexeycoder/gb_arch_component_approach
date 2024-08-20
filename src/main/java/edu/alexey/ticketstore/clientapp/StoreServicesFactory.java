package edu.alexey.ticketstore.clientapp;

public interface StoreServicesFactory {

	StoreServices forExistingCustomer(String loginName, String password);

	StoreServices forNewCustomer(String loginName, String password, long cardNumber);
}
