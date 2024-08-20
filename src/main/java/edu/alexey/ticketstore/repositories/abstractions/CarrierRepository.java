package edu.alexey.ticketstore.repositories.abstractions;

import edu.alexey.ticketstore.entities.Carrier;

/**
 * Интерфейс взаимодействия с базой перевозчиков
 */
public interface CarrierRepository {

	/**
	 * Получить модель перевозчика из базы по ID
	 *
	 * @param carrierId идентификатор перевозчика в базе
	 * @return
	 */
	Carrier read(int carrierId);
}
