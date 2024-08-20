package edu.alexey.ticketstore.entities;

import java.util.Objects;

/**
 * Покупатель (ORM-сущность)
 */
public class Customer {

	private int customerId;
	private String name;
	private String password;
	private long cardNumber;

	public Customer(int customerId, String name, String password, long cardNumber) {
		this.customerId = customerId;
		this.name = name;
		this.password = password;
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, customerId, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		return cardNumber == other.cardNumber && customerId == other.customerId && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return String.format(
				"Customer [customerId=%s, name=%s, password=%s, cardNumber=%016d]",
				customerId, name, password, cardNumber);
	}

}
