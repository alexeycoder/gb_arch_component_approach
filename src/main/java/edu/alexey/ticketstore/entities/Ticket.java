package edu.alexey.ticketstore.entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Билет (ORM-сущность)
 */
public class Ticket {

	private int thicketId;
	private int routeId;
	private int placeId;
	private int price;
	private LocalDate date;
	private boolean isValid;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getThicketId() {
		return thicketId;
	}

	public int getRouteId() {
		return routeId;
	}

	public int getPlaceId() {
		return placeId;
	}

	public int getPrice() {
		return price;
	}

	public LocalDate getDate() {
		return date;
	}

	public Ticket(int ticketId, int routeId, int placeId, int price, LocalDate date) {
		this.thicketId = ticketId;
		this.routeId = routeId;
		this.placeId = placeId;
		this.price = price;
		this.date = date;
		this.isValid = true;
	}

	@Override
	public String toString() {
		return "Ticket" +
				" Route Number " + routeId +
				", Place " + placeId +
				", Price " + price + " rub." +
				", Date " + date +
				", " + (isValid ? "Free" : "Busy");
	}

	public String toPrint() {
		return "Ticket" +
				"\nRoute Number " + routeId +
				"\nPlace " + placeId +
				"\nPrice " + price + "rub." +
				"\nDate " + date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, isValid, placeId, price, routeId, thicketId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ticket))
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(date, other.date)
				&& isValid == other.isValid
				&& placeId == other.placeId
				&& price == other.price
				&& routeId == other.routeId
				&& Objects.equals(thicketId, other.thicketId); // на случай, если для ORM-сущности придётся boxed-поле типа Integer делать
	}

}
