package edu.alexey.ticketstore.services;

import java.util.Objects;

import edu.alexey.ticketstore.entities.Carrier;
import edu.alexey.ticketstore.repositories.abstractions.CarrierRepository;

public class CarrierService {

	private final CarrierRepository carrierRepository;

	public CarrierService(CarrierRepository carrierRepository) {
		this.carrierRepository = Objects.requireNonNull(carrierRepository);
	}

	public Carrier read(int carrierId) {

		return carrierRepository.read(carrierId);
	}

}
