package com.reltessinger.upscaleEnergy.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.objects.Address;

@Component
public class AddressEntity {
	
	public Map<Integer,Address> mapAddress = new HashMap<Integer, Address>();

}
