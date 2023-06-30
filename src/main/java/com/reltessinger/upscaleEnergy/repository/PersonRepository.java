package com.reltessinger.upscaleEnergy.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.reltessinger.upscaleEnergy.objects.Person;

@Component
public class PersonRepository {
	
	public Map<Integer,Person> mapPerson = new HashMap<Integer, Person>();

}
