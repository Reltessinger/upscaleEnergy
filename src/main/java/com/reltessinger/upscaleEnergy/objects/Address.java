package com.reltessinger.upscaleEnergy.objects;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.objects.records.AddressRecord;

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
@EqualsAndHashCode(of = {"postalCode", "street", "number"})
public class Address {
		
	private Integer id;
	private String street;
	private Integer number;
	private String district;
	private String postalCode;
	private String city;
	private String state;
	private String country;
	private Boolean active;
	
	public AddressRecord toAddressRecord() {
		AddressRecord oAddressRecord = new AddressRecord(this.street, this.number, this.district, this.postalCode, this.city, this.state, this.country, this.active);
		return oAddressRecord;
	}
	
}
