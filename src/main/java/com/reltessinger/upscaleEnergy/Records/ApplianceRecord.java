package com.reltessinger.upscaleEnergy.Records;

import com.reltessinger.upscaleEnergy.objects.Appliance;

public record ApplianceRecord(String name, String model, double power, String powerUnit, String active) {

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
