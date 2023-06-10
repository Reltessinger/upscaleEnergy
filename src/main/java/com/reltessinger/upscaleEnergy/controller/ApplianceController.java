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
import com.reltessinger.upscaleEnergy.entity.ApplianceEntity;
import com.reltessinger.upscaleEnergy.objects.Appliance;
import com.reltessinger.upscaleEnergy.objects.records.ApplianceRecord;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

	@Autowired
	private ApplianceEntity oApplianceEntity;
	
	@Autowired
	private ApplicationConfiguration oApplicationConfiguration;
	
	@GetMapping
	public ResponseEntity<Collection<?>> getAllAppliance() {
		Collection<Appliance> oAppliance = oApplianceEntity.mapAppliance.values();
		
		return ResponseEntity.ok(oAppliance);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getApplianceById(@PathVariable Integer id) {
		Appliance oAppliance = oApplianceEntity.mapAppliance.get(id);
		if(oAppliance == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appliance id "+ id + ", not found.");
			
		return ResponseEntity.ok(oAppliance.toApplianceRecord());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid ApplianceRecord oApplianceRecord) {
		int idAppliance = new Random().nextInt(Integer.MAX_VALUE);
		Appliance oAppliance = oApplianceRecord.toAppliance();
		oAppliance.setId(idAppliance);
		oApplianceEntity.mapAppliance.put(idAppliance,oAppliance);
		
		return ResponseEntity.created(oApplicationConfiguration.getURI("/appliance/", idAppliance)).body(oAppliance);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid ApplianceRecord oApplianceRecord) {
		Appliance oAppliance = oApplianceRecord.toAppliance();
		oAppliance.setId(id);
		if(oApplianceEntity.mapAppliance.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appliance id "+ id + ", not found.");
		
		oApplianceEntity.mapAppliance.put(id,oAppliance);
		
		return ResponseEntity.ok("Appliance successfully updated!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		if(oApplianceEntity.mapAppliance.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appliance id "+ id + ", not found.");
		
		oApplianceEntity.mapAppliance.remove(id);
		
		return ResponseEntity.ok("Appliance deleted successfully.");
	}
	
}
