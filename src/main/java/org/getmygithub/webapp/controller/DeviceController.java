package org.getmygithub.webapp.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.getmygithub.webapp.exception.ResourceNotFoundException;
import org.getmygithub.webapp.model.Device;
import org.getmygithub.webapp.service.DeviceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("devices")
public class DeviceController {

	private DeviceService deviceService;
	
	/**
	 * @param deviceService
	 */
	@Inject
	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "application/json" media type.
	 * 
	 * @param consumerKey
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//public List<Product> getProductList(@NotBlank(message = "Consumerkey is required") @QueryParam(value = "consumerKey") String consumerKey) {
	public List<Device> getDeviceList() {
		//log.info("Consumer: {}", consumerKey);
		return deviceService.findAll();
	}

	@GET
	@Path("{deviceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Device getDevice(@PathParam(value = "deviceId") Long deviceId) {
		return deviceService.findById(deviceId).orElseThrow(() -> new ResourceNotFoundException("deviceId " + deviceId + " not found"));
	}

	@POST
	public @Valid Device createDevice(@Valid Device device) {
		deviceService.save(device);
		return device;
	}

	@PUT
	@Path("{deviceId}")
	public Device updateDevice(@PathParam(value = "deviceId") Long deviceId, @Valid Device device) {
		return deviceService.findById(deviceId).map(p -> {
			p.setName(device.getName());
			p.setIp(device.getIp());
			p.setMonitored(device.getMonitored());
			p.setReachable(device.getReachable());
			p.setType(device.getType());
			p.setLog(device.getLog());
			deviceService.update(p);
			return device;
		}).orElseThrow(() -> new ResourceNotFoundException("deviceId " + deviceId + " not found"));
	}

	@DELETE
	@Path("{deviceId}")
	public String deleteDevice(@PathParam(value = "deviceId") Long deviceId) {
		return deviceService.findById(deviceId).map(p -> {
			deviceService.deleteById(deviceId);
			return "Device deleted";
		}).orElseThrow(() -> new ResourceNotFoundException("deviceId " + deviceId + " not found"));
	}
	
}
