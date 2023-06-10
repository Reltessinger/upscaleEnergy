package com.reltessinger.upscaleEnergy.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.objects.Appliance;

@Component
public class ApplianceEntity {
	
	public Map<Integer,Appliance> mapAppliance = new HashMap<Integer, Appliance>();

}
