package edu.umbc.contacttracingbleserver.repository;

import org.springframework.data.repository.CrudRepository;

import edu.umbc.contacttracingbleserver.model.InfectedDetails;  


public interface InfectionRepository extends CrudRepository<InfectedDetails, String>{


}  
