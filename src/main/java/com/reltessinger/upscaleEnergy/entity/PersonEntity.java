package com.reltessinger.upscaleEnergy.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.objects.Person;

@Component
public class PersonEntity {
	
	public static Map<Integer,Person> mapPerson = new HashMap<Integer, Person>();

}
