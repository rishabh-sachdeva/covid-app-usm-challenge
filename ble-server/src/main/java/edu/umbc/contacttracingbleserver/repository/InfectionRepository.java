package edu.umbc.contacttracingbleserver.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.umbc.contacttracingbleserver.model.InfectedDetails;  


public interface InfectionRepository extends CrudRepository<InfectedDetails, String>{

	@Query("Select a from InfectedDetails a where a.time_stamp >= :time_stamp")
	List<InfectedDetails> findAllWithTimestampAfter(@Param("time_stamp") Date date);
}  
