package com.reltessinger.upscaleEnergy.controller;

import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.reltessinger.upscaleEnergy.config.ApplicationConfiguration;
import com.reltessinger.upscaleEnergy.objects.Person;
import com.reltessinger.upscaleEnergy.objects.records.PersonRecord;
import com.reltessinger.upscaleEnergy.repository.PersonRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepository oPersonRepository;
	
	@Autowired
	private ApplicationConfiguration oApplicationConfiguration;
	
	@GetMapping
	public ResponseEntity<Collection<?>> getAllPerson() {
		Collection<Person> oPerson = oPersonRepository.mapPerson.values();
		
		return ResponseEntity.ok(oPerson);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable Integer id) {
		Person oPerson= oPersonRepository.mapPerson.get(id);
		if(oPerson == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person id "+ id + ", not found.");
			
		return ResponseEntity.ok(oPerson.toPersonRecord());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid PersonRecord oPersonRecord) {
		int idPerson = new Random().nextInt(Integer.MAX_VALUE);
		Person oPerson = oPersonRecord.toPerson();
		oPerson.setId(idPerson);

		if(oPersonRepository.mapPerson.values().contains(oPerson))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is already a person with this fiscal number.");
		
		oPersonRepository.mapPerson.put(idPerson,oPerson);
		
		return ResponseEntity.created(oApplicationConfiguration.getURI("/person/", idPerson)).body(oPerson);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid PersonRecord oPersonRecord) {
		Person oPerson = oPersonRecord.toPerson();
		oPerson.setId(id);
		if(oPersonRepository.mapPerson.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person id "+ id + ", not found.");
		
		oPersonRepository.mapPerson.put(id,oPerson);
		
		return ResponseEntity.ok("Person successfully updated!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		if(oPersonRepository.mapPerson.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person id "+ id + ", not found.");
		
		oPersonRepository.mapPerson.remove(id);
		
		return ResponseEntity.ok("Person deleted successfully.");
	}
	
}
