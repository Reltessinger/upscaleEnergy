package com.reltessinger.upscaleEnergy.objects.records;

import com.reltessinger.upscaleEnergy.objects.Appliance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ApplianceRecord(
		@NotBlank
		@Size(min = 5)
		String name,
		@NotBlank
		String model,
		@NotNull
		double power,
		@NotNull
		PowerUnit powerUnit, 
		@NotNull
		String active) {

	public Appliance toAppliance() {
		Appliance oAppliance = new Appliance();
		oAppliance.setName(this.name());
		oAppliance.setModel(this.model());
		oAppliance.setPower(this.power());
		oAppliance.setPowerUnit(this.powerUnit());
		oAppliance.setActive(this.active());
		return oAppliance;
	}
	
}
