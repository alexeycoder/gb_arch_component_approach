package edu.alexey.ticketstore.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.alexey.ticketstore.entities.Carrier;
import edu.alexey.ticketstore.repositories.abstractions.CarrierRepository;

/**
 * Демонстрационный репозиторий, имитирующий работу с БД перевозчиков
 */
public class DemoCarrierRepository implements CarrierRepository {

	private static final List<Carrier> CARRIERS;

	static {
		// Заполняем базу данных
		CARRIERS = new ArrayList<>(
				List.of(new Carrier(1, 1)));
	}

	@Override
	public Carrier read(int carrierId) throws RuntimeException {

		for (var carrier : CARRIERS) {
			if (Objects.equals(carrier.getCarrierId(), carrierId)) {
				return carrier;
			}
		}
		throw new RuntimeException("A carrier with this ID not found");
	}
}
