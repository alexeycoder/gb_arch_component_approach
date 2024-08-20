package edu.alexey.ticketstore.services;

import java.util.List;
import java.util.Objects;

import edu.alexey.ticketstore.entities.Ticket;
import edu.alexey.ticketstore.repositories.abstractions.TicketRepository;

public class TicketService {

	private final TicketRepository ticketRepository;

	public TicketService(TicketRepository ticketRepository) {
		this.ticketRepository = Objects.requireNonNull(ticketRepository);
	}

	public boolean create(Ticket ticket) {
		return ticketRepository.create(ticket);
	}

	public List<Ticket> findValidByRoute(int routeId) {
		return ticketRepository.findValidByRoute(routeId);
	}

	public boolean update(Ticket ticket) {
		return ticketRepository.update(ticket);
	}

	public boolean delete(Ticket ticket) {
		return ticketRepository.delete(ticket);
	}

}
