package org.getmygithub.webapp.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.getmygithub.webapp.model.Device;
import org.getmygithub.webapp.repository.DeviceRepository;

public class DeviceServiceImp implements DeviceService {

	private DeviceRepository deviceRepository;
	
	@Inject
	public DeviceServiceImp(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}
	
	@Override
	public Device save(Device device) {
		return deviceRepository.save(device);
	}

	@Override
	public Device update(Device device) {
		return deviceRepository.update(device);
	}

	@Override
	public void deleteById(Long id) {
		deviceRepository.deleteById(id);		
	}

	@Override
	public Optional<Device> findById(Long id) {
		return deviceRepository.findById(id);
	}

	@Override
	public List<Device> findAll() {
		return deviceRepository.findAll();
	}

}
