package edu.alexey.ticketstore.clientapp;

import java.time.LocalDate;
import java.util.List;

import edu.alexey.ticketstore.entities.Ticket;
import edu.alexey.ticketstore.exceptions.PurchaseException;
import edu.alexey.ticketstore.exceptions.TransactionException;

/**
 * Интерфейс взаимодействия с клиентским приложением
 */
public interface StoreService {

	String getCustomerDetails();

	/**
	 * Метод покупки билета
	 *
	 * @param ticket билет
	 * @throws TransactionException
	 */
	void purchaseTicket(Ticket ticket) throws PurchaseException;

	/**
	 * Метод поиска билетов по дате и номеру маршрута
	 *
	 * @param date  дата
	 * @param route номер маршрута
	 * @return список доступных для приобретения билетов
	 */
	List<Ticket> findAvailableTickets(LocalDate date, int routeId);
}
