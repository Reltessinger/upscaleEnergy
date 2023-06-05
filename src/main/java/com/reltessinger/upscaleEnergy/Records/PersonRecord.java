package com.reltessinger.upscaleEnergy.Records;

import java.util.Calendar;
import java.util.List;

import com.reltessinger.upscaleEnergy.objects.Address;
import com.reltessinger.upscaleEnergy.objects.Person;

public record PersonRecord(String name, String lastName, Calendar dateBirth, String fiscalNumber, char gender,
		List<Address> lstAddress, Boolean active) {

	public Person toPerson() {
		Person oPerson = new Person();
		oPerson.setName(this.name());
		oPerson.setLastName(this.lastName());
		oPerson.setDateBirth(this.dateBirth());
		oPerson.setFiscalNumber(this.fiscalNumber());
		oPerson.setGender(this.gender());
		oPerson.setLstAddress(this.lstAddress());
		oPerson.setActive(this.active());
		return oPerson;
	}
}
