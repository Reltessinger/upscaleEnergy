package com.reltessinger.upscaleEnergy.objects.records;

import java.util.Calendar;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.reltessinger.upscaleEnergy.objects.Address;
import com.reltessinger.upscaleEnergy.objects.Gender;
import com.reltessinger.upscaleEnergy.objects.Person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record PersonRecord(
		@NotBlank
		@Size(min = 3)
		String name, 
		@NotBlank
		@Size(min = 3)
		String lastName,
		@NotNull
		@Past
		Calendar dateBirth,
		@Email
		String email,
		String mobile,
		@NotBlank
		@CPF
		String fiscalNumber, 
		@NotNull
		Gender gender,
		List<Address> lstAddress, 
		@NotNull
		Boolean active) {

	public Person toPerson() {
		Person oPerson = new Person();
		oPerson.setName(this.name());
		oPerson.setLastName(this.lastName());
		oPerson.setDateBirth(this.dateBirth());
		oPerson.setEmail(this.email());
		oPerson.setMobile(this.mobile());
		oPerson.setFiscalNumber(this.fiscalNumber());
		oPerson.setGender(this.gender());
		oPerson.setLstAddress(this.lstAddress());
		oPerson.setActive(this.active());
		return oPerson;
	}
}
