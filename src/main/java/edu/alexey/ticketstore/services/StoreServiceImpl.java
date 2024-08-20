package edu.alexey.ticketstore.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import edu.alexey.ticketstore.clientapp.StoreService;
import edu.alexey.ticketstore.entities.Carrier;
import edu.alexey.ticketstore.entities.Customer;
import edu.alexey.ticketstore.entities.Ticket;
import edu.alexey.ticketstore.exceptions.PurchaseException;
import edu.alexey.ticketstore.exceptions.TransactionException;
import edu.alexey.ticketstore.repositories.DemoSingletoneDbContext;
import edu.alexey.ticketstore.repositories.abstractions.DbContext;
import edu.alexey.ticketstore.services.abstractions.PaymentService;

public class StoreServiceImpl implements StoreService {

	private final DbContext context;
	private final PaymentService paymentService;

	private final Customer customer;

	public StoreServiceImpl(Customer customer) {
		this.customer = Objects.requireNonNull(customer);

		this.context = DemoSingletoneDbContext.getInstance();
		this.paymentService = DemoSingletonePaymentService.getInstance();
	}

	@Override
	public String getCustomerDetails() {

		return String.format(
				"Логин %s, идентификатор %s, номер карты %016d",
				customer.getLoginName(),
				customer.getCustomerId(),
				customer.getCardNumber());
	}

	@Override
	public void purchaseTicket(Ticket ticket) throws PurchaseException {

		if (!Objects.requireNonNull(ticket).isAvailable()) {
			throw new IllegalArgumentException("Invalid ticket state: the ticket has already been purchased.");
		}

		Carrier carrier = context.getCarrierRepository().read(1);

		try {
			paymentService.transaction(ticket.getPrice(), customer.getCardNumber(), carrier.getCardNumber());

		} catch (TransactionException e) {
			throw new PurchaseException(e);
		}

		ticket.setAvailable(false);
		try {
			context.geTicketRepository().update(ticket);

		} catch (Exception e) {
			throw new PurchaseException(e);
		}
	}

	@Override
	public List<Ticket> findAvailableTickets(LocalDate date, int routeId) {

		return context.geTicketRepository()
				.findAvailableByRoute(routeId)
				.stream()
				.filter(t -> t.getDate().equals(date))
				.toList();
	}
}
