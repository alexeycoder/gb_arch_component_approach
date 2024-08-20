package edu.alexey.ticketstore.repositories.abstractions;

import java.util.List;

import edu.alexey.ticketstore.entities.Ticket;

/**
 * Интерфейс взаимодействия с базой билетов
 */
public interface TicketRepository {

	/**
	 * Создать новый билет
	 *
	 * @param ticket
	 * @return
	 */
	boolean create(Ticket ticket);

	/**
	 * Получить список билетов по номеру маршрута
	 *
	 * @param routeId
	 * @return
	 */
	List<Ticket> findValidByRoute(int routeId);

	/**
	 * Обновить билет
	 *
	 * @param ticket
	 * @return
	 */
	boolean update(Ticket ticket);

	/**
	 * Удалить билет
	 *
	 * @param ticket
	 * @return
	 */
	boolean delete(Ticket ticket);
}
