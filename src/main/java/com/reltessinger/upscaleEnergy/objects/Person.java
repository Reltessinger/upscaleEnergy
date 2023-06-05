package com.reltessinger.upscaleEnergy.objects;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.Records.PersonRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	private Integer id;
	private String name;
	private String lastName;
	private Calendar dateBirth;
	private String fiscalNumber;
	private char gender;
	private List<Address> lstAddress;
	private Boolean active;
	
	public PersonRecord toPersonRecord() {
		PersonRecord oPersonRecord = new PersonRecord(this.name, this.lastName, this.dateBirth, this.fiscalNumber, this.gender, this.lstAddress, this.active);
		return oPersonRecord;
	}
}
