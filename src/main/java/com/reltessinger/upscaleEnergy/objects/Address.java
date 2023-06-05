package com.reltessinger.upscaleEnergy.objects;

import java.util.List;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.Records.AddressRecord;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {

	private Integer id;
	private String street;
	private Integer number;
	private String district;
	private String postalCode;
	private String city;
	private String state;
	private Boolean active;
	private List<Appliance> lstAppliance;
	
	public AddressRecord toAddressRecord() {
		AddressRecord oAddressRecord = new AddressRecord(this.street, this.number, this.district, this.postalCode, this.city, this.state, this.active);
		return oAddressRecord;
	}
}