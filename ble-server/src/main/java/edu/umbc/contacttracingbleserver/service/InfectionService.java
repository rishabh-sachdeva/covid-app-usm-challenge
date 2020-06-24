package edu.umbc.contacttracingbleserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.umbc.contacttracingbleserver.model.InfectedDetails;
import edu.umbc.contacttracingbleserver.repository.InfectionRepository;

@Service
public class InfectionService {
	
	@Autowired
	InfectionRepository repo;
	
	public List<InfectedDetails> getAllInfectedIds(){
		List<InfectedDetails> details = new ArrayList<InfectedDetails>();
		repo.findAll().forEach(infD -> details.add(infD));
		return details;
	}
	
	public void addInfectedId(InfectedDetails detail) {
		repo.save(detail);
	}

}
