package cn.org.mingframework.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.ModelAndView;

public class MingModelAndView extends ModelAndView {
	public void setViewName(String viewName, HttpServletRequest request){
		super.setViewName(getDeviceType(getDevice(request)) + "/" + viewName);
	}
	
	private Device getDevice(HttpServletRequest request){
		return DeviceUtils.getCurrentDevice(request);
	}
	
	private String getDeviceType(Device device){
		if(device.isMobile()){
			return "mobil";
		}else if(device.isTablet()){
			return "tablet";
		}else{
			return "normal";
		}
	}
}
