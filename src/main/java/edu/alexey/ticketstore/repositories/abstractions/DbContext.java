package edu.alexey.ticketstore.repositories.abstractions;

public interface DbContext {

	CustomerRepository getCustomerRepository();

	CarrierRepository getCarrierRepository();

	TicketRepository geTicketRepository();
}
