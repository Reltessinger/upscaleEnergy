package com.reltessinger.upscaleEnergy.Records;

import com.reltessinger.upscaleEnergy.objects.Address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AddressRecord(
		@NotNull
		@Size(min=5, max=100, message="Rua deve ter entre 5 e 100 caracteres")
		String street,
		@NotNull
		@Positive
		Integer number, String district, String postalCode, String city, String state, Boolean active) {

	public Address toAddress() {
		Address oAddress = new Address();
		oAddress.setStreet(this.street());
		oAddress.setNumber(this.number());
		oAddress.setDistrict(this.district());
		oAddress.setPostalCode(this.postalCode());
		oAddress.setCity(this.city());
		oAddress.setState(this.state());
		oAddress.setActive(this.active());
		return oAddress;
	}
}
