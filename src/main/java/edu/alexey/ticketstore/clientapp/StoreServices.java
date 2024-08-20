package edu.alexey.ticketstore.clientapp;

import java.time.LocalDate;
import java.util.List;

import edu.alexey.ticketstore.entities.Ticket;

/**
 * Интерфейс взаимодействия с клиентским приложением
 */
public interface StoreServices {

	String getCustomerDetails();

	/**
	 * Метод покупки билета
	 *
	 * @param ticket билет
	 * @return успешность выполненной операции
	 * @throws RuntimeException
	 */
	boolean purchaseTicket(Ticket ticket) throws RuntimeException;

	/**
	 * Метод поиска билетов по дате и номеру маршрута
	 *
	 * @param date  дата
	 * @param route номер маршрута
	 * @return список доступных для приобретения билетов
	 * @throws RuntimeException
	 */
	List<Ticket> findAvailableTickets(LocalDate date, int routeId) throws RuntimeException;
}
