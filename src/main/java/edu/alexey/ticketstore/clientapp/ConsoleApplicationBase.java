package edu.alexey.ticketstore.clientapp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Базовый абстрактный класс клиентского приложения, обеспечивающий базовые
 * функции.
 */
public abstract class ConsoleApplicationBase {

	static final Scanner SCANNER = new Scanner(System.in);

	static final String DIV_STR = "=".repeat(80);

	/**
	 * Метод ввода и валидации целого числа в диапазоне
	 *
	 * @param min минимальное число
	 * @param max максимальное число
	 * @return введенное целое число
	 * @throws RuntimeException
	 */
	protected int inputInt(int min, int max) throws RuntimeException {
		int num = 0;
		try {
			num = SCANNER.nextInt();
		} catch (InputMismatchException ex) {
			throw new RuntimeException("This is not number.");
		} catch (Exception ex) {
			throw new RuntimeException("Something wrong.");
		}
		if (num < min || num > max) {
			throw new RuntimeException("You entered an invalid value.");
		}
		return num;
	}

	/**
	 * Метод ввода и валидации целого числа в диапазоне
	 *
	 * @param min минимальное число
	 * @param max максимальное число
	 * @return введенное целое число
	 * @throws RuntimeException
	 */
	protected long inputLong(long min, long max) throws RuntimeException {
		long num = 0;
		try {
			num = SCANNER.nextLong();
		} catch (InputMismatchException ex) {
			throw new RuntimeException("This is not number.");
		} catch (Exception ex) {
			throw new RuntimeException("Something wrong.");
		}
		if (num < min || num > max) {
			throw new RuntimeException("You entered an invalid value.");
		}
		return num;
	}

	/**
	 * Метод ввода строки и ее валидация на пустую строку
	 *
	 * @return строку
	 */
	protected String inputString() throws RuntimeException {
		String str;
		try {
			str = SCANNER.next();
		} catch (Exception ex) {
			throw new RuntimeException("Something wrong.");
		}
		if (str.isEmpty()) {
			throw new RuntimeException("You must something enter");
		}
		return str;
	}

	/**
	 * Meтод ввода даты и ее валидация
	 *
	 * @return дата
	 * @throws RuntimeException
	 */
	protected LocalDate inputDate() throws RuntimeException {

		String str;
		LocalDate date;
		try {
			str = SCANNER.nextLine();
		} catch (Exception ex) {
			throw new RuntimeException("Something wrong.");
		}
		if (str.isEmpty()) {
			throw new RuntimeException("You must something enter");
		}
		try {
			date = LocalDate.parse(str);
		} catch (DateTimeParseException ex) {
			throw new RuntimeException("You must enter date");
		}
		return date;
	}
}
