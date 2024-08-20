package edu.alexey.ticketstore.repositories;

import edu.alexey.ticketstore.repositories.abstractions.TransactionRepository;

/**
 * Демонстрационный репозиторий, имитирующий работы с платёжным сервисом
 */
public class DemoTransactionRepository implements TransactionRepository {

	@Override
	public void transaction(int amount, long cardFrom, long cardTo) {

		if (!checkIfCardValid(cardTo)) {
			throw new RuntimeException("No recipient account.");
		}
		if (!checkIfCardValid(cardFrom)) {
			throw new RuntimeException("No withdrawal account.");
		}
		if (!checkFunds(cardFrom, amount)) {
			throw new RuntimeException("Insufficient funds.");
		}
		if (!canAcceptPayment(cardTo, amount)) {
			throw new RuntimeException("Recipient cannot accept payment.");
		}

		try {
			processTransaction(amount, cardFrom, cardTo);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private boolean checkIfCardValid(long cardNumber) {
		// TODO ...
		return true;
	}

	private boolean checkFunds(long cardNumber, int amount) {
		// TODO ...
		return true;
	}

	private boolean canAcceptPayment(long cardNumber, int amount) {
		// TODO ...
		return true;
	}

	private void processTransaction(int amount, long cardFrom, long cardTo) {

	}

}
