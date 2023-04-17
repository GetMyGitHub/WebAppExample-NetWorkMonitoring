package org.getmygithub.webapp.listener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.getmygithub.webapp.business.MonitoringDevices;
import org.getmygithub.webapp.model.Device;
import org.getmygithub.webapp.repository.DeviceRepository;
import org.getmygithub.webapp.utils.CustomQueryParam;


@WebListener
public class StartupListener implements ServletContextListener {
	
	
	public void contextInitialized(ServletContextEvent event) {
		
		System.out.println("app started");	
		
		MonitoringDevices monitoringDevices = new MonitoringDevices();
		monitoringDevices.StartMonitoring();		
		
//		DeviceRepository dr = new DeviceRepository();
//		
//		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);		
//		
//		Runnable task = () -> {
//			
//			
//			String query = " e.lastReached < :oneMinuteAgo";
//			List<CustomQueryParam> listCustomQueryParam = new ArrayList<CustomQueryParam>();
//			CustomQueryParam param1 = new CustomQueryParam("oneMinuteAgo", new Date(System.currentTimeMillis() - (1 * 60 * 1000)));
//			listCustomQueryParam.add(param1);
//			List<Device> listDevices = dr.findAllByCustomCriteria(dr.createTypedQuery(query, listCustomQueryParam));			
//			for (Iterator<Device> iterator = listDevices.iterator(); iterator.hasNext();) {
//				Device device = (Device) iterator.next();
//				dr.deleteById(device.getId());				
//			}			
//			
//			InetAddress address;
//			Boolean reacheable = null;
//			
//			try {
//				address = InetAddress.getByName("192.168.1.103");
//				reacheable = address.isReachable(100);
//			} catch (UnknownHostException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//			
//			LocalDateTime localDateTime = LocalDateTime.now();
//			Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//			Device device = new Device();
//			device.setName("PC-HUGO");
//			device.setIp("192.168.1.103");
//			device.setType(Device.Type.COMPUTER);
//			device.setLastReached(date);
//			device.setReachable(reacheable);
//			dr.save(device);		
//
//
//		};
//		
//		executor.scheduleAtFixedRate(task, 0, 30, TimeUnit.SECONDS);
		
		
		
		
		
	}

	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("app ended");

	}

}
