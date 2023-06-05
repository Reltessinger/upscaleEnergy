package com.reltessinger.upscaleEnergy.controller;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.reltessinger.upscaleEnergy.Records.AddressRecord;
import com.reltessinger.upscaleEnergy.entity.AddressEntity;
import com.reltessinger.upscaleEnergy.objects.Address;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;

@RestController
@RequestMapping("/address")
public class AddressController {

	@GetMapping
	public ResponseEntity<Collection<?>> getAllAddress() {
		Collection<Address> oAddress = AddressEntity.mapAddress.values();
		
		return ResponseEntity.status(HttpStatus.OK).body(oAddress);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAddressById(@PathVariable Integer id) {
		Address oAddress = AddressEntity.mapAddress.get(id);
		if(oAddress == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address id "+ id + ", not found.");
			
		return ResponseEntity.status(HttpStatus.OK).body(oAddress.toAddressRecord());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody AddressRecord oAddressRecord) {
		Set<ConstraintViolation<AddressRecord>> notValidated = Validation.buildDefaultValidatorFactory().getValidator().validate(oAddressRecord);
		Map<Path, String> notValidatedToMap = notValidated.stream().collect(Collectors.toMap(notValidated1 ->  notValidated1.getPropertyPath(), notValidated1 ->  notValidated1.getMessage()));
		
		if(!notValidatedToMap.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notValidatedToMap);
			
		int idAddress = new Random().nextInt(Integer.MAX_VALUE);
		Address oAddress = oAddressRecord.toAddress();
		oAddress.setId(idAddress);
		AddressEntity.mapAddress.put(idAddress,oAddress);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(oAddress);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AddressRecord oAddressRecord) {
		Address oAddress = oAddressRecord.toAddress();
		oAddress.setId(id);
		if(AddressEntity.mapAddress.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address id "+ id + ", not found.");
		
		AddressEntity.mapAddress.put(id,oAddress);
		
		return ResponseEntity.status(HttpStatus.OK).body(oAddress.toAddressRecord());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		if(AddressEntity.mapAddress.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address id "+ id + ", not found.");
		
		AddressEntity.mapAddress.remove(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Address deleted successfully.");
	}
	
}
