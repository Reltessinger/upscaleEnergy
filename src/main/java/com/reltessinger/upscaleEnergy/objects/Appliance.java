package com.reltessinger.upscaleEnergy.objects;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.objects.records.ApplianceRecord;
import com.reltessinger.upscaleEnergy.objects.records.PowerUnit;

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
@EqualsAndHashCode(of = {"name", "model", "power"})
public class Appliance {
	
	private Integer id;
	private String name;
	private String model;
	private double power;
	private PowerUnit powerUnit;
	private String active;

	public ApplianceRecord toApplianceRecord() {
		ApplianceRecord oApplianceRecord = new ApplianceRecord(this.name, this.model, this.power, this.powerUnit, this.active);
		return oApplianceRecord;
	}
		
}
