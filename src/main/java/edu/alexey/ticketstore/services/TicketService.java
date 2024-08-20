package edu.alexey.ticketstore.services;

import java.util.Objects;

import edu.alexey.ticketstore.repositories.abstractions.TicketRepository;

public class TicketService {

	private final TicketRepository ticketRepository;

	public TicketService(TicketRepository ticketRepository) {
		this.ticketRepository = Objects.requireNonNull(ticketRepository);
	}

}
