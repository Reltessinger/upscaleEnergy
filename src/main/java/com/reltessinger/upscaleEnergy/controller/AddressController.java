package com.reltessinger.upscaleEnergy.controller;

import java.net.URISyntaxException;
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
import com.reltessinger.upscaleEnergy.objects.Address;
import com.reltessinger.upscaleEnergy.objects.records.AddressRecord;
import com.reltessinger.upscaleEnergy.repository.AddressRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressRepository oAddressRepository;
	
	@Autowired
	private ApplicationConfiguration oApplicationConfiguration;
	
	@GetMapping
	public ResponseEntity<Collection<?>> getAllAddress() {
		Collection<Address> oAddress = oAddressRepository.mapAddress.values();
		
		return ResponseEntity.ok(oAddress);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAddressById(@PathVariable Integer id) {
		Address oAddress = oAddressRepository.mapAddress.get(id);
		if(oAddress == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address id "+ id + ", not found.");
			
		return ResponseEntity.ok(oAddress.toAddressRecord());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid AddressRecord oAddressRecord) throws URISyntaxException  {
		int idAddress = new Random().nextInt(Integer.MAX_VALUE);
		Address oAddress = oAddressRecord.toAddress();
		oAddress.setId(idAddress);
		oAddressRepository.mapAddress.put(idAddress,oAddress);
		
		return ResponseEntity.created(oApplicationConfiguration.getURI("/address/",idAddress)).body(oAddress);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid AddressRecord oAddressRecord) {
		Address oAddress = oAddressRecord.toAddress();
		oAddress.setId(id);
		if(oAddressRepository.mapAddress.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address id "+ id + ", not found.");
		
		oAddressRepository.mapAddress.put(id,oAddress);
		
		return ResponseEntity.ok("Address successfully updated!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		if(oAddressRepository.mapAddress.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address id "+ id + ", not found.");
		
		oAddressRepository.mapAddress.remove(id);
		
		return ResponseEntity.ok("Address deleted successfully.");
	}
		
}
