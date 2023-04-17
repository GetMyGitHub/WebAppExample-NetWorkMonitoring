package org.getmygithub.webapp.utils;

public class CustomQueryParam {

	private String identity;
	private Object value;
	
	public CustomQueryParam(String identity, Object value) {
		this.identity = identity;
		this.value = value;
	}
	public String getIdentity() {
		return identity;
	}

	public Object getValue() {
		return value;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
	
}
