package edu.alexey.ticketstore.repositories.abstractions;

import java.util.List;

import edu.alexey.ticketstore.entities.Customer;
import edu.alexey.ticketstore.exceptions.AlreadyExistingCustomerException;

/**
 * Интерфейс взаимодействия с базой клиентов
 */
public interface CustomerRepository {

	/**
	 * Создать нового покупателя (клиента)
	 *
	 * @param loginName
	 * @param passwordHash
	 * @param cardNumber
	 * @return
	 */
	Customer create(String loginName, String password, long cardNumber) throws AlreadyExistingCustomerException;

	/**
	 * получить пользователя по ID (для тестов, в приложении не используется)
	 *
	 * @param customerId
	 * @return
	 */
	Customer read(int customerId);

	/**
	 * Найти пользователя по имени для входа
	 *
	 * @param loginName
	 * @return
	 */
	Customer findByLoginName(String loginName);

	/**
	 * Получить список пользователей (для тестов, в приложении не используется)
	 *
	 * @return
	 */
	List<Customer> readAll();

	/**
	 * Обновить пользователя (для тестов, в приложении не используется)
	 *
	 * @param customer
	 * @return
	 */
	boolean update(Customer customer);

	/**
	 * Удалить пользователя (для тестов, в приложении не используется)
	 *
	 * @param customer
	 * @return
	 */
	boolean delete(Customer customer);
}
