package edu.alexey.ticketstore.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.alexey.ticketstore.entities.Ticket;
import edu.alexey.ticketstore.repositories.abstractions.TicketRepository;

public class DemoTicketRepository implements TicketRepository {

	private static final ArrayList<Ticket> TICKETS;

	static {
		TICKETS = new ArrayList<Ticket>();
		generateTickets(1, 6, 10, LocalDate.of(2024, 8, 1));
		generateTickets(2, 4, 15, LocalDate.of(2024, 8, 10));
	}

	@Override
	public boolean create(Ticket ticket) {

		TICKETS.add(ticket);
		return true;
	}

	@Override
	public List<Ticket> findAvailableByRoute(int routeId) {
		List<Ticket> routeTickets = new ArrayList<>();
		for (Ticket ticket : TICKETS) {
			if (ticket.getRouteId() == routeId && ticket.isAvailable()) {
				routeTickets.add(ticket);
			}
		}
		return routeTickets;
	}

	@Override
	public boolean update(Ticket ticket) {

		for (Ticket aTicket : TICKETS) {
			if (aTicket.getThicketId() == ticket.getThicketId()) {
				TICKETS.remove(aTicket);
				TICKETS.add(ticket);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Ticket ticket) {

		if (TICKETS.contains(ticket)) {
			TICKETS.remove(ticket);
			return true;
		}
		return false;
	}

	private static void generateTickets(int routeId, int countPlaces, int price, LocalDate date) {

		for (int i = 1; i <= countPlaces; i++) {
			TICKETS.add(new Ticket(i, routeId, i, price, date));
		}
	}

}
