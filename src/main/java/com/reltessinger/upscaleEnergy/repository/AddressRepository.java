package com.reltessinger.upscaleEnergy.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.objects.Address;

@Component
public class AddressRepository {
	
	public Map<Integer,Address> mapAddress = new HashMap<Integer, Address>();

}
