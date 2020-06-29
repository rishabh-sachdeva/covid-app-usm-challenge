package edu.umbc.contacttracingbleserver.model;

public class SucceessRes {
	
	public SucceessRes() {
		
	}
	
public SucceessRes(String msg) {
		this.msg = msg;
	}


	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
