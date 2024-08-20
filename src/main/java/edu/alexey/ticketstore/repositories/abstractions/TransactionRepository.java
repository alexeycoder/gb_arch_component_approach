package edu.alexey.ticketstore.repositories.abstractions;

/**
 * Интерфейс взаимодействия с базой сервиса платежей
 */
public interface TransactionRepository {

	/**
	 * Метод проведения транзакции
	 *
	 * @param amount   размер транзакции
	 * @param cardFrom карта покупателя
	 * @param cardTo   карта продавца
	 */
	void transaction(int amount, long cardFrom, long cardTo);
}
