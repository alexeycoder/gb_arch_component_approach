package edu.alexey.ticketstore.repositories;

import edu.alexey.ticketstore.repositories.abstractions.CarrierRepository;
import edu.alexey.ticketstore.repositories.abstractions.CustomerRepository;
import edu.alexey.ticketstore.repositories.abstractions.DbContext;
import edu.alexey.ticketstore.repositories.abstractions.TicketRepository;

public class DemoSingletoneDbContext implements DbContext {

	public static DbContext getInstance() {
		return Holder.INSTANCE;
	}

	private final CustomerRepository customerRepository = new DemoCustomerRepository();
	private final CarrierRepository carrierRepository = new DemoCarrierRepository();
	private final TicketRepository ticketRepository = new DemoTicketRepository();

	@Override
	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	@Override
	public CarrierRepository getCarrierRepository() {
		return carrierRepository;
	}

	@Override
	public TicketRepository geTicketRepository() {
		return ticketRepository;
	}

	private static class Holder {
		static final DbContext INSTANCE = new DemoSingletoneDbContext();
	}
}
