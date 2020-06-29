package edu.umbc.contacttracingbleserver.model;

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
//    @Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	private long time_stamp;
	/*
	 * @Column private double latitude; public double getLatitude() { return
	 * latitude; } public void setLatitude(double latitude) { this.latitude =
	 * latitude; } public double getLongitude() { return longitude; } public void
	 * setLongitude(double longitude) { this.longitude = longitude; }
	@Column
	private double longitude;
	*/

	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}
	
	public long getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(long time_stamp) {
		this.time_stamp = time_stamp;
	}

}
