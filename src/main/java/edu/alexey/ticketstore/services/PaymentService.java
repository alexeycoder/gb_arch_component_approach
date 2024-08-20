package edu.alexey.ticketstore.services;

import java.util.Objects;

import edu.alexey.ticketstore.repositories.abstractions.TransactionRepository;

public class PaymentService {

	private final TransactionRepository transactionRepository;

	public PaymentService(TransactionRepository transactionRepository) {

		this.transactionRepository = Objects.requireNonNull(transactionRepository);
	}

	public boolean transaction(int amount, long cardFrom, long cardTo) {

		try {
			transactionRepository.transaction(amount, cardFrom, cardTo);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
