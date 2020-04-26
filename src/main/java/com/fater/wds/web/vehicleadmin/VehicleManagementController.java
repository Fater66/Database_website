package com.fater.wds.web.vehicleadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fater.wds.dto.DriverExecution;
import com.fater.wds.dto.VehicleExecution;
import com.fater.wds.entity.Account;
import com.fater.wds.entity.Customer;
import com.fater.wds.entity.Driver;
import com.fater.wds.entity.Vehicle;
import com.fater.wds.enums.DriverStateEnum;
import com.fater.wds.enums.VehicleStateEnum;
import com.fater.wds.service.DriverService;
import com.fater.wds.service.VehicleService;
import com.fater.wds.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/vehicleadmin")
public class VehicleManagementController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "/getvehiclelistbypolicyid",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getVehicleListByPolicyId(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long policyId = HttpServletRequestUtil.getLong(request, "policyId");
		if(policyId >1)
		{
			try {
				VehicleExecution ve = vehicleService.queryVehicleByPolicyId(policyId);
				modelMap.put("vehicleList",ve.getVehicleList());
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
			modelMap.put("errMsg","empty policy id");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/getvehiclelist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getVehicleList(HttpServletRequest request) {
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
			VehicleExecution he = vehicleService.queryVehicleByCustomerId(customer.getCustomerId());
			modelMap.put("vehicleList", he.getVehicleList());
			modelMap.put("customer", customer);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/getvehiclebyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getVehicleById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long vehicleId = HttpServletRequestUtil.getLong(request, "vehicleId");
		if (vehicleId > 1) {
			try {
				Vehicle vehicle = vehicleService.getByVehicleId(vehicleId);
				modelMap.put("success", true);
				modelMap.put("vehicle", vehicle);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty vehicle id");
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifyvehicle", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyVehicle(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String vehicleStr = HttpServletRequestUtil.getString(request, "vehicleStr");
		ObjectMapper mapper = new ObjectMapper();
		Vehicle vehicle = null;
		try {
			vehicle = mapper.readValue(vehicleStr, Vehicle.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		if (vehicle != null) {
			long vehicleId = Long.valueOf(HttpServletRequestUtil.getString(request, "vehicleId"));
			vehicle.setVehicleId(vehicleId);
			VehicleExecution he = vehicleService.modifyVehicle(vehicle);
			if (he.getState() == VehicleStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", he.getStateInfo());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "Please enter the vehicle information");
			return modelMap;
		}
	}

	@RequestMapping(value = "/registervehicle", method = RequestMethod.POST)
	@ResponseBody
	//用户提交的信息都会被保存在httpservletrequest中
	private Map<String, Object> registerVehicle(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		//1.接受前端信息，利用mapper转换为实体类，包括vehicle信息
		String vehicleStr = HttpServletRequestUtil.getString(request, "vehicleStr");
		ObjectMapper mapper = new ObjectMapper();
		Vehicle vehicle = null;
		try {
			vehicle = mapper.readValue(vehicleStr, Vehicle.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//2.注册vehicle
		if (vehicle != null) {
			Account account = (Account) request.getSession().getAttribute("account");
			vehicle.setCustomerId(account.getCustomer().getCustomerId());
			//session to do
			VehicleExecution ve = vehicleService.addVehicle(vehicle);
			if (ve.getState() == VehicleStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ve.getStateInfo());
				return modelMap;
			}
			Long[] driverVehicleArray = HttpServletRequestUtil.getLongArray(request, "driverVehicleListStr");
			//设定driver关联的vehicle
			if(driverVehicleArray.length >0)
			{
				for(int i =0;i<driverVehicleArray.length; i++)
				{
					Long driverId = driverVehicleArray[i];
					Long vehicleId = vehicle.getVehicleId();
					Driver driver = driverService.getByDriverId(driverId);
					if(driver == null)
					{
						modelMap.put("success", false);
						modelMap.put("errMsg", "Driver is not available");
						return modelMap;
					}
					driver.setVehicleId(vehicleId);
					DriverExecution de = driverService.modifyDriver(driver);
					if(de.getState() != DriverStateEnum.SUCCESS.getState())
					{
						modelMap.put("success", false);
						modelMap.put("errMsg", de.getStateInfo());
						return modelMap;
					}
				}
			}
			else
			{
				
				modelMap.put("success", false);
				modelMap.put("errMsg", "need to select at least one home for home insurance");
				return modelMap;
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please enter the vehicle information");
			return modelMap;
		}
	}
}
