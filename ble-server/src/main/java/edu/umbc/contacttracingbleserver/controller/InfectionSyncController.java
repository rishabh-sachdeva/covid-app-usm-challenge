package edu.umbc.contacttracingbleserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.umbc.contacttracingbleserver.model.InfectedDetails;
import edu.umbc.contacttracingbleserver.model.SucceessRes;
import edu.umbc.contacttracingbleserver.service.InfectionService;

@RestController  
public class InfectionSyncController {

	@Autowired  
	InfectionService inf_service;
	
	@GetMapping("/allInfected")
	private List<InfectedDetails> getInfectedIds(){
		System.out.println("GET Called**********");
		return inf_service.getAllInfectedIds();
	}
	@GetMapping("/allInfectedOneDay")
	private List<InfectedDetails> getInfectedIdsLastDay(){
		System.out.println("GET ALL one day Called**********");
		return inf_service.getIdsLast24h();
	}
	@PostMapping("/addInfected")
	private SucceessRes saveInfectedId(@RequestBody InfectedDetails detail) {
		System.out.println("POST Called********** with key: "+ detail.getPublic_key());
		detail.setTime_stamp(System.currentTimeMillis());//set current time
		inf_service.addInfectedId(detail);
		return new SucceessRes(detail.getPublic_key());
	}
}
