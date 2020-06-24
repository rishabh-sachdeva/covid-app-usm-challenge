package edu.umbc.contacttracingbleserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "InfectedDetails")
public class InfectedDetails {
	
	@Id
	@Column
	private String public_key;
	@Column
	private LocalDateTime time_stamp;

	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}
	
	public LocalDateTime getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(LocalDateTime time_stamp) {
		this.time_stamp = time_stamp;
	}

}
