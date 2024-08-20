package edu.alexey.ticketstore.clientapp;

import java.time.LocalDate;
import java.util.List;

import edu.alexey.ticketstore.entities.Ticket;
import edu.alexey.ticketstore.exceptions.AlreadyExistingCustomerException;
import edu.alexey.ticketstore.exceptions.AuthorizationException;
import edu.alexey.ticketstore.exceptions.PurchaseException;
import edu.alexey.ticketstore.services.StoreServiceFactoryImpl;

/**
 * Основной класс клиентского приложения.
 */
public class ConsoleApplication extends ConsoleApplicationBase {

	private final StoreServiceFactory storeServicesFactory = new StoreServiceFactoryImpl();
	// Связь с основной логикой осуществляется через интерфейс StoreServices
	private StoreService customerStoreServices;

	private int ticketRouteId;
	private LocalDate ticketDate;

	/**
	 * Метод запуска меню входа и регистрации
	 */
	public void runLifecycle() {

		boolean run = true;
		while (run) {
			printMessageLine("Приложение сервиса покупки транспортных билетов");
			printMessageLine("Чтобы войти как зарегистрированный клиент введите 1\n"
					+ "Для регистрации нового клиента введите 2"
					+ "\nДля завершения приложения введите 0");
			System.out.print("?> ");
			int choice = 0;
			try {
				choice = inputInt(0, 2);
			} catch (RuntimeException ex) {
				System.err.println(ex.getMessage());
				continue;
			}
			System.out.println(DIV_STR);
			run = runLoginRegisterMenuChoiceLogic(choice);
		}
	}

	/**
	 * Логика ветвления запуска программы
	 *
	 * @param choice
	 * @return
	 */
	private boolean runLoginRegisterMenuChoiceLogic(int choice) {

		customerStoreServices = null;

		switch (choice) {
		case 1:

			if (login()) {
				runBuyingMenu();
			}
			break;

		case 2:
			if (register()) {
				runBuyingMenu();
			}
			break;

		default:
			return false;
		}
		return true;
	}

	/**
	 * Меню входа зарегистрированного пользователя
	 */
	private boolean login() {

		printMessageLine("Вход для существующего клиента");
		System.out.print("Имя для входа: ");
		String loginName = inputString();
		System.out.print("Пароль: ");
		String password = inputString();
		System.out.println(DIV_STR);

		try {

			customerStoreServices = storeServicesFactory.forExistingCustomer(loginName, password);
			if (customerStoreServices != null) {

				System.out.print("Вы успешно вошли в систему...");
				return true;
			} else {
				System.out.println("Такой пользователь не зарегистрирован в системе");
				return false;
			}

		} catch (AuthorizationException e) {

			System.out.println("Неудачная попытка входа в систему.");
			System.out.println(e.getMessage());
			System.out.println(DIV_STR);

		} catch (Exception e) {

			System.out.println("Неизвестная ошибка при попытке входа в систему.");
			e.printStackTrace();
			System.out.println(DIV_STR);
		}
		return false;
	}

	/**
	 * Меню регистрации нового пользователя
	 */
	private boolean register() {

		printMessageLine("Регистрация нового клиента");
		System.out.print("Введите желаемое имя для входа: ");
		String loginName = inputString();
		System.out.print("Задайте пароль: ");
		String password = inputString();
		System.out.print("Повторите пароль: ");
		String password2 = inputString();

		if (!password.equals(password2)) {
			printMessageLine("Ошибка регистрации: Введёные пароли не совпадают.");
			System.out.println(DIV_STR);
			return false;
		}

		System.out.print("Введите номер банковской карты: ");
		long cardNumber = inputLong(1L, 9999_9999_9999_9999L);
		System.out.println(DIV_STR);

		try {

			customerStoreServices = storeServicesFactory.forNewCustomer(loginName, password, cardNumber);
			System.out.print("Вы успешно зарегистрировались в системе.");
			return true;

		} catch (AlreadyExistingCustomerException e) {

			System.out.println(
					"Возникла ошибка при регистрации: невозможно зарегистрировать клиента с указанными данными.");
			System.out.println(e.getMessage());
			System.out.println(DIV_STR);
		} catch (Exception e) {

			System.out.println("Неизвестная ошибка при регистрации.");
			e.printStackTrace();
			System.out.println(DIV_STR);
		}
		return false;
	}

	/**
	 * Меню покупки билетов
	 */
	private void runBuyingMenu() {

		assert customerStoreServices != null : "customerStoreService";
		boolean run = true;
		while (run) {
			printHeader("Приложение для покупки транспортных билетов");

			printMessageLine("Чтобы выбрать маршрут и посмотреть доступные посадочные места введите 1,\n" +
					"Чтобы завершить введите 0");
			System.out.print("?> ");
			int choice = 0;
			try {
				choice = inputInt(0, 1);
			} catch (RuntimeException ex) {
				System.out.println(DIV_STR);
				printMessageLine(ex.getMessage());
				continue;
			}
			System.out.println(DIV_STR);
			run = runBuyingMenuChoiceLogic(choice);
		}
	}

	/**
	 * Логика ветвления меню покупки билетов
	 *
	 * @param choice
	 * @return
	 */
	private boolean runBuyingMenuChoiceLogic(int choice) {
		switch (choice) {
		case 1:
			ticketRouteId = runSelectRouteMenu();
			if (ticketRouteId > 0) {
				ticketDate = runSelectDate();
				if (ticketDate != null) {

					List<Ticket> availableTickets = null;

					try {
						availableTickets = customerStoreServices.findAvailableTickets(ticketDate, ticketRouteId);

					} catch (RuntimeException ex) {

						printMessageLine(ex.getMessage());
						return true;
					}

					printTickets(availableTickets);
					buyTicketMenu(availableTickets);
					return true;
					//return;
				}
				return true;
			}
			return true;
		default:
			return false;
		}
	}

	/**
	 * Метод ввода номера маршрута
	 *
	 * @return номер маршрута
	 */
	private int runSelectRouteMenu() {
		printHeader("Выбор маршрута и даты");
		System.out.print("Введите номер маршрута: ");
		//Здесь ограничиваем число маршрутов. У на всего 2 маршрута.
		int numRoute;
		try {
			numRoute = inputInt(1, 2);
		} catch (RuntimeException ex) {
			printMessageLine(ex.getMessage());
			return -1;
		}
		System.out.println(DIV_STR);
		return numRoute;
	}

	/**
	 * Метод ввода даты поездки
	 *
	 * @return дата поездки
	 */
	private LocalDate runSelectDate() {
		System.out.print("Введите дату в формате YYYY-MM-DD: ");
		//Здесь ограничиваем число маршрутов. У на всего 2 маршрута.
		LocalDate date;
		try {
			date = inputDate();
		} catch (RuntimeException ex) {
			printMessageLine(ex.getMessage());
			return null;
		}
		System.out.println(DIV_STR);
		return date;
	}

	/**
	 * Метод вывода в консоль списка билетов
	 *
	 * @param ticks список билетов
	 */
	private void printTickets(List<Ticket> tickets) {
		for (var ticket : tickets) {
			System.out.println(ticket.toString());
		}
		System.out.println(DIV_STR);
	}

	/**
	 * Метод покупки билета
	 */
	private void buyTicketMenu(List<Ticket> availableTickets) {

		printHeader("Подтверждение покупки билета");
		System.out.print("Чтобы приобрести билет по маршруту " + ticketRouteId
				+ " на дату " + ticketDate
				+ "введите Yes: ");
		String answer = inputString();
		System.out.println(DIV_STR);
		buyTicketMenuConfirmLogic(availableTickets, answer);
	}

	/**
	 * Логика ветвления меню подтверждения покупки
	 *
	 * @param answer
	 */
	private void buyTicketMenuConfirmLogic(List<Ticket> availableTickets, String answer) {
		if (answer.equalsIgnoreCase("YES")) {
			for (var ticket : availableTickets) {

				if (ticket.getDate().equals(ticketDate) && ticket.getRouteId() == ticketRouteId
						&& ticket.isAvailable()) {

					try {
						customerStoreServices.purchaseTicket(ticket);
						printMessageLine("Билет успешно приобретён\n" + ticket.toPrint());

					} catch (PurchaseException ex) {
						printMessageLine("Ошибка. Не удалось приобрести билет!\n" + ex.getMessage());
					}
				}
			}
		}
	}

	private void printHeader(String header) {
		printMessageLine(header + "\nКлиент: " + customerStoreServices.getCustomerDetails());
	}

	/**
	 * Метод вывода в консоль элемента сообщения
	 *
	 * @param message
	 */
	private void printMessageLine(String message) {
		System.out.println(message);
		System.out.println(DIV_STR);
	}
}
