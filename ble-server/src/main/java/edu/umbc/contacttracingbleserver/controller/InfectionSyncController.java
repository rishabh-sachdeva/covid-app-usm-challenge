package edu.umbc.contacttracingbleserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.umbc.contacttracingbleserver.model.InfectedDetails;
import edu.umbc.contacttracingbleserver.service.InfectionService;

@RestController  
public class InfectionSyncController {

	@Autowired  
	InfectionService inf_service;
	
	@GetMapping("/allInfected")
	private List<InfectedDetails> getInfectedIds(){
		return inf_service.getAllInfectedIds();
	}
	@GetMapping("/allInfectedOneDay")
	private List<InfectedDetails> getInfectedIdsLastDay(){
		return inf_service.getIdsLast24h();
	}
	@PostMapping("/addInfected")
	private String saveInfectedId(@RequestBody InfectedDetails detail) {
		inf_service.addInfectedId(detail);
		return detail.getPublic_key();
	}
}
