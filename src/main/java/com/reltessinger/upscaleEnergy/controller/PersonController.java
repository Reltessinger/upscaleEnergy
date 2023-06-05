package com.reltessinger.upscaleEnergy.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reltessinger.upscaleEnergy.Records.PersonRecord;
import com.reltessinger.upscaleEnergy.entity.AddressEntity;
import com.reltessinger.upscaleEnergy.entity.PersonEntity;
import com.reltessinger.upscaleEnergy.objects.Address;
import com.reltessinger.upscaleEnergy.objects.Person;

@RestController
@RequestMapping("/person")
public class PersonController {

	@GetMapping
	public ResponseEntity<Collection<?>> getAllPerson() {
		Collection<Person> oPerson = PersonEntity.mapPerson.values();
		
		return ResponseEntity.status(HttpStatus.OK).body(oPerson);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable Integer id) {
		Person oPerson= PersonEntity.mapPerson.get(id);
		if(oPerson == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person id "+ id + ", not found.");
			
		return ResponseEntity.status(HttpStatus.OK).body(oPerson.toPersonRecord());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PersonRecord oPersonRecord) {
		int idPerson = new Random().nextInt(Integer.MAX_VALUE);
		Person oPerson = oPersonRecord.toPerson();
		oPerson.setId(idPerson);
		List<Address> lstAddressRemove = new ArrayList<Address>();
		if(oPerson.getLstAddress()!=null) {
			for (int i = 0; i<oPerson.getLstAddress().size(); i++) {				
				Address oAddress = AddressEntity.mapAddress.get(oPerson.getLstAddress().get(i).getId());
				if(oAddress!=null)
					oPerson.getLstAddress().set(i, oAddress);
				else
					lstAddressRemove.add(oPerson.getLstAddress().get(i));
			}
			oPerson.getLstAddress().removeAll(lstAddressRemove);
		}
		PersonEntity.mapPerson.put(idPerson,oPerson);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(oPerson);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PersonRecord oPersonRecord) {
		Person oPerson = oPersonRecord.toPerson();
		oPerson.setId(id);
		if(PersonEntity.mapPerson.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person id "+ id + ", not found.");
		
		PersonEntity.mapPerson.put(id,oPerson);
		
		return ResponseEntity.status(HttpStatus.OK).body(oPerson.toPersonRecord());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		if(PersonEntity.mapPerson.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person id "+ id + ", not found.");
		
		PersonEntity.mapPerson.remove(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.");
	}
	
}
