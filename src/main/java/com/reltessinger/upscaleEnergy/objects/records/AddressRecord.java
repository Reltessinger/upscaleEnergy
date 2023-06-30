package com.reltessinger.upscaleEnergy.objects.records;

import com.reltessinger.upscaleEnergy.objects.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AddressRecord(
		@NotBlank
		@Size(min=5, max=100, message="Rua deve ter entre 5 e 100 caracteres")
		String street,
		@NotNull
		@Positive
		Integer number, 
		@NotBlank
		@Size(min=3, max=255, message="Bairro deve ter entre 3 e 255 caracteres")
		String district, 
		@NotBlank
		@Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato CEP inválido")
		String postalCode,
		@NotBlank
		@Size(min=5, max=100, message="Rua deve ter entre 5 e 100 caracteres")
		String city,
		@NotBlank
		@Size(min=3, max=100, message="Rua deve ter entre 3 e 100 caracteres")
		String state,
		@NotBlank
		@Size(min=5, max=100, message="Rua deve ter entre 5 e 100 caracteres")
		String country,
		@NotNull
		Boolean active) {
	
		public Address toAddress() {
			Address oAddress = new Address();
			oAddress.setStreet(this.street());
			oAddress.setNumber(this.number());
			oAddress.setDistrict(this.district());
			oAddress.setPostalCode(this.postalCode());
			oAddress.setCity(this.city());
			oAddress.setState(this.state());
			oAddress.setCountry(this.country());
			oAddress.setActive(this.active());
			return oAddress;
		}
	
	
}
