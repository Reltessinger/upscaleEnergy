package com.reltessinger.upscaleEnergy.objects;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.objects.records.PersonRecord;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"fiscalNumber"})
public class Person {
	
	private Integer id;
	private String name;
	private String lastName;
	private Calendar dateBirth;
	private String email;
	private String mobile;
	private String fiscalNumber;
	private Gender gender;
	private List<Address> lstAddress;
	private Boolean active;
	
	public PersonRecord toPersonRecord() {
		PersonRecord oPersonRecord = new PersonRecord(this.name, this.lastName, this.dateBirth, this.email, this.mobile, this.fiscalNumber, this.gender, this.lstAddress, this.active);
		return oPersonRecord;
	}
	
}
