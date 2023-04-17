package org.getmygithub.webapp.business;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.getmygithub.webapp.model.Device;
import org.getmygithub.webapp.repository.DeviceRepository;
import org.getmygithub.webapp.utils.CustomQueryParam;

public class MonitoringDevices {
	
	private DeviceRepository deviceRepository;
	
	private List<Device> listDevices = new ArrayList<Device>();
	
	private ScheduledExecutorService getMonitoredDevices;
	

	public MonitoringDevices() {	
		getMonitoredDevices = Executors.newScheduledThreadPool(1);
	}
	
	public void StartMonitoring() {
		getMonitoredDevices.scheduleAtFixedRate(getMonitoredDevicesTask, 0, 10, TimeUnit.SECONDS);
	}

	private DeviceRepository getDeviceRepository() {
		if(deviceRepository == null) deviceRepository = new DeviceRepository();		
		return deviceRepository;
	}
	
	
	private Runnable getMonitoredDevicesTask = () -> {
		String query = " e.monitored = :booleanValue";
		List<CustomQueryParam> listCustomQueryParam = new ArrayList<CustomQueryParam>();
		CustomQueryParam param1 = new CustomQueryParam("booleanValue", true);
		listCustomQueryParam.add(param1);
		listDevices = getDeviceRepository().findAllByCustomCriteria(
				getDeviceRepository().createTypedQuery(query, listCustomQueryParam)
				);		
		for (Device device : listDevices) {
			InetAddress address;
			Boolean reacheable = null;			
			try {
				System.out.println(device.getIp());
				address = InetAddress.getByName(device.getIp());
				reacheable = address.isReachable(1000);				
				Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());				
				if(reacheable) {
					device.setLog(String.format("%s %s is connected", date, device.getName()));
					device.setReachable(true);
					device.setLastReached(date);
				} else {
					device.setReachable(false);
					device.setLog(String.format("%s %s is disconnected", date, device.getName()));
				}
				getDeviceRepository().save(device);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}		
		
	};
	
	
}
