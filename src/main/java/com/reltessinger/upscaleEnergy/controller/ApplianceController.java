package com.reltessinger.upscaleEnergy.controller;

import java.util.Collection;
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

import com.reltessinger.upscaleEnergy.Records.ApplianceRecord;
import com.reltessinger.upscaleEnergy.entity.ApplianceEntity;
import com.reltessinger.upscaleEnergy.objects.Appliance;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

	@GetMapping
	public ResponseEntity<Collection<?>> getAllAppliance() {
		Collection<Appliance> oAppliance = ApplianceEntity.mapAppliance.values();
		
		return ResponseEntity.status(HttpStatus.OK).body(oAppliance);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getApplianceById(@PathVariable Integer id) {
		Appliance oAppliance = ApplianceEntity.mapAppliance.get(id);
		if(oAppliance == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appliance id "+ id + ", not found.");
			
		return ResponseEntity.status(HttpStatus.OK).body(oAppliance.toApplianceRecord());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ApplianceRecord oApplianceRecord) {
		int idAppliance = new Random().nextInt(Integer.MAX_VALUE);
		Appliance oAppliance = oApplianceRecord.toAppliance();
		oAppliance.setId(idAppliance);
		ApplianceEntity.mapAppliance.put(idAppliance,oAppliance);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(oAppliance);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ApplianceRecord oApplianceRecord) {
		Appliance oAppliance = oApplianceRecord.toAppliance();
		oAppliance.setId(id);
		if(ApplianceEntity.mapAppliance.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appliance id "+ id + ", not found.");
		
		ApplianceEntity.mapAppliance.put(id,oAppliance);
		
		return ResponseEntity.status(HttpStatus.OK).body(oAppliance.toApplianceRecord());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		if(ApplianceEntity.mapAppliance.get(id)==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appliance id "+ id + ", not found.");
		
		ApplianceEntity.mapAppliance.remove(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Appliance deleted successfully.");
	}
	
}
