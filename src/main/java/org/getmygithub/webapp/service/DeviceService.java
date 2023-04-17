package org.getmygithub.webapp.service;

import java.util.List;
import java.util.Optional;

import org.getmygithub.webapp.model.Device;


public interface DeviceService {
	Device save(Device device);

	Device update(Device device);

	void deleteById(Long id);

	Optional<Device> findById(Long id);

	List<Device> findAll();
}
