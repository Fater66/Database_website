package com.fater.wds.web.driveradmin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fater.wds.dto.DriverExecution;
import com.fater.wds.entity.Account;
import com.fater.wds.entity.Administrater;
import com.fater.wds.entity.Customer;
import com.fater.wds.entity.Driver;
import com.fater.wds.enums.DriverStateEnum;
import com.fater.wds.service.DriverService;
import com.fater.wds.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/driveradmin")
public class DriverManagementController {

	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "/listdriver",method = RequestMethod.GET)
	//ResponseBody将返回对象自动转换成json
	@ResponseBody
	private Map<String,Object> listDriver()
	{
		Map<String,Object> modelMap = new HashMap<String, Object>();
		List<Driver> driverList = new ArrayList<Driver>();
		try {
			driverList = driverService.getAllDriverList();
			modelMap.put("driverList",driverList);
			modelMap.put("success",true);
		}catch(Exception e)
		{
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/deletedriver",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delelteDriver(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long driverId = HttpServletRequestUtil.getLong(request, "driverId");
		if(driverId > -1)
		{
			try {
				DriverExecution ce = driverService.deleteDriver(driverId);
				if(ce.getState() == DriverStateEnum.SUCCESS.getState())
				{
					modelMap.put("success",true);
				}
				else
				{
					modelMap.put("success",false);
					modelMap.put("errMsg",ce.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		}
		else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty driverId");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/getdriverlistbyvehicleid",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getDriverListByVehicleId(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long vehicleId = HttpServletRequestUtil.getLong(request, "vehicleId");
		if(vehicleId >1)
		{
			try {
				DriverExecution de = driverService.queryDriverByVehicleId(vehicleId);
				modelMap.put("driverList",de.getDriverList());
				modelMap.put("success",true);
		}catch(Exception e)
			{	
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			}
		}
		else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty vehicle id");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/searchdriverlist",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> searchDriverList(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		
		Administrater administrater = (Administrater)request.getSession().getAttribute("administrater");
		Account account = (Account)request.getSession().getAttribute("account");
		if(administrater == null)
		{
			
			if(account == null )
			{
				modelMap.put("success", false);
				modelMap.put("errMsg","need to log in");
				return modelMap;
			}
			if(account.getCustomer() == null)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg","need to create customer information");
				return modelMap;
			}
		}
		Customer customer = (account != null)? account.getCustomer():null;
		
		try {
			String driverConditionStr = HttpServletRequestUtil.getString(request, "driverConditionStr");
			Date minDate = null;
			if(HttpServletRequestUtil.getString(request, "minDateStr")!=null)
			{
				minDate = Date.valueOf(HttpServletRequestUtil.getString(request, "minDateStr"));
			}
			Date maxDate = null;
			if(HttpServletRequestUtil.getString(request, "maxDateStr")!=null)
			{
				maxDate = Date.valueOf(HttpServletRequestUtil.getString(request, "maxDateStr"));
			}
			Driver driverCondition = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				driverCondition = mapper.readValue(driverConditionStr, Driver.class);
			}catch(Exception e)
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
				return modelMap;
			}
			if(administrater == null) driverCondition.setCustomerId(customer.getCustomerId());
			
			DriverExecution de = driverService.getDriverList(driverCondition, minDate, maxDate);
			modelMap.put("driverList",de.getDriverList());
			modelMap.put("customer",customer);
			modelMap.put("success",true);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/getdriverlist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getDriverList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		Account account = (Account) request.getSession().getAttribute("account");
		if (account == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "need to log in");
			return modelMap;
		}
		if (account.getCustomer() == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "need to create customer information");
			return modelMap;
		}
		Customer customer = account.getCustomer();
		try {
			DriverExecution he = driverService.queryDriverByCustomerId(customer.getCustomerId());
			modelMap.put("driverList", he.getDriverList());
			modelMap.put("customer", customer);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/getdriverbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getDriverById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long driverId = HttpServletRequestUtil.getLong(request, "driverId");
		if (driverId > 1) {
			try {
				Driver driver = driverService.getByDriverId(driverId);
				modelMap.put("success", true);
				modelMap.put("driver", driver);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty driver id");
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifydriver", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyDriver(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String driverStr = HttpServletRequestUtil.getString(request, "driverStr");
		ObjectMapper mapper = new ObjectMapper();
		Driver driver = null;
		try {
			driver = mapper.readValue(driverStr, Driver.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		if (driver != null) {
			long driverId = Long.valueOf(HttpServletRequestUtil.getString(request, "driverId"));
			driver.setDriverId(driverId);
			DriverExecution he = driverService.modifyDriver(driver);
			if (he.getState() == DriverStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", he.getStateInfo());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "Please enter the driver information");
			return modelMap;
		}
	}

	@RequestMapping(value = "/registerdriver", method = RequestMethod.POST)
	@ResponseBody
	//用户提交的信息都会被保存在httpservletrequest中
	private Map<String, Object> registerDriver(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		//1.接受前端信息，利用mapper转换为实体类，包括driver信息
		String driverStr = HttpServletRequestUtil.getString(request, "driverStr");
		ObjectMapper mapper = new ObjectMapper();
		Driver driver = null;
		try {
			driver = mapper.readValue(driverStr, Driver.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//2.注册driver
		if (driver != null) {
			Account account = (Account) request.getSession().getAttribute("account");
			driver.setCustomerId(account.getCustomer().getCustomerId());
			//session to do
			DriverExecution ve = driverService.addDriver(driver);
			if (ve.getState() == DriverStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ve.getStateInfo());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please enter the driver information");
			return modelMap;
		}
	}
}
