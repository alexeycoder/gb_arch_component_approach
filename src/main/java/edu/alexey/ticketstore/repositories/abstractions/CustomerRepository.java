package edu.alexey.ticketstore.repositories.abstractions;

import java.util.List;

import edu.alexey.ticketstore.entities.Customer;

/**
 * Интерфейс взаимодействия с базой клиентов
 */
public interface CustomerRepository {

	/**
	 * Создать нового покупателя (клиента)
	 *
	 * @param name
	 * @param passwordHash
	 * @param cardNumber
	 * @return
	 */
	int create(String name, String password, long cardNumber);

	/**
	 * получить пользователя по ID (для тестов, в приложении не используется)
	 *
	 * @param customerId
	 * @return
	 */
	Customer read(int customerId);

	/**
	 * Найти пользователя по имени
	 *
	 * @param customerName
	 * @return
	 */
	Customer findByName(String customerName);

	/**
	 * Получить список пользователей (для тестов, в приложении не используется)
	 *
	 * @return
	 */
	List<Customer> readAll();

	/**
	 * Обновить пользователя (для тестов, в приложении не используется)
	 *
	 * @param client
	 * @return
	 */
	boolean update(Customer customer);

	/**
	 * Удалить пользователя (для тестов, в приложении не используется)
	 *
	 * @param client
	 * @return
	 */
	boolean delete(Customer customer);
}
