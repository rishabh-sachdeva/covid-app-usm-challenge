package edu.umbc.contacttracingbleserver.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	public List<InfectedDetails> getIdsLast24h(){
		Calendar cal = Calendar.getInstance();
		List<InfectedDetails> details = new ArrayList<InfectedDetails>();
		cal.add(Calendar.DATE,-1);
		System.out.println("FETCHING BY DATE");
		repo.findAllWithTimestampAfter(cal.getTime()).forEach(infD->details.add(infD));
		return details;
	}
	public void addInfectedId(InfectedDetails detail) {
		repo.save(detail);
	}

}
