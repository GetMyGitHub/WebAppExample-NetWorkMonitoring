package org.getmygithub.webapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Device {

	public enum Type {
		Computer, Phone, Tablet
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "Device ip is required")
	private String ip;

	private Date lastReached;

	private String log;

	private Boolean monitored;

	@NotBlank(message = "Device name is required")
	private String name;

	private Boolean reachable;

	private Type type;

	public Long getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public Date getLastReached() {
		return lastReached;
	}

	public String getLog() {
		return log;
	}

	public Boolean getMonitored() {
		return monitored;
	}

	public String getName() {
		return name;
	}

	public Boolean getReachable() {
		return reachable;
	}

	public Type getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setLastReached(Date lastReached) {
		this.lastReached = lastReached;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public void setMonitored(Boolean monitored) {
		this.monitored = monitored;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReachable(Boolean reachable) {
		this.reachable = reachable;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", name=" + name + ", ip=" + ip + ", type=" + type + ", reachable=" + reachable
				+ ", lastReached=" + lastReached + ", monitored=" + monitored + "]";
	}

}
