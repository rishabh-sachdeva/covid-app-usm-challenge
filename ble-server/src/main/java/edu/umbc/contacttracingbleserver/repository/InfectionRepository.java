package edu.umbc.contacttracingbleserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.umbc.contacttracingbleserver.model.InfectedDetails;  


public interface InfectionRepository extends CrudRepository<InfectedDetails, String>{

	@Query("Select a from InfectedDetails a where a.time_stamp >= :time_stamp")
	List<InfectedDetails> findAllWithTimestampAfter(@Param("time_stamp") long date);
//	@Query("Select public_key from InfectedDetails a where a.time_stamp >= :time_stamp")
//	List<String> findAllWithTimestampAfter(@Param("time_stamp") long date);

}  
