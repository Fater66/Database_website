package com.fater.wds.web.customeradmin;

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
import com.fater.wds.dto.CustomerExecution;
import com.fater.wds.entity.Customer;
import com.fater.wds.enums.CustomerStateEnum;
import com.fater.wds.service.CustomerService;
import com.fater.wds.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/customeradmin")
public class CustomerManagementController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/listcustomer",method = RequestMethod.GET)
	//ResponseBody将返回对象自动转换成json
	@ResponseBody
	private Map<String,Object> listCustomer()
	{
		Map<String,Object> modelMap = new HashMap<String, Object>();
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			customerList = customerService.getCustomerList();
			modelMap.put("customerList",customerList);
			modelMap.put("success",true);
		}catch(Exception e)
		{
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/searchcustomerlist",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> searchCustomerList(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		
		try {
			String customerConditionStr = HttpServletRequestUtil.getString(request, "customerConditionStr");
			Customer customerCondition = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				customerCondition = mapper.readValue(customerConditionStr, Customer.class);
			}catch(Exception e)
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
				return modelMap;
			}
			CustomerExecution pe = customerService.getCustomerListByCondition(customerCondition);
			modelMap.put("customerList",pe.getCustomerList());
			modelMap.put("success",true);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	@RequestMapping(value = "/getcustomerbyid",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCustomerById(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long customerId = HttpServletRequestUtil.getLong(request, "customerId");
		
		if(customerId > -1)
		{
			try {
				Customer customer = customerService.getByCustomerId(customerId);
				modelMap.put("customer",customer);
				modelMap.put("success",true);
			} catch (Exception e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		}
		else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty customerId");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/deletecustomer",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delelteCustomer(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long customerId = HttpServletRequestUtil.getLong(request, "customerId");
		if(customerId > -1)
		{
			try {
				CustomerExecution ce = customerService.deleteCustomer(customerId);
				if(ce.getState() == CustomerStateEnum.SUCCESS.getState())
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
			modelMap.put("errMsg","empty customerId");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/modifycustomer",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> modifyCustomer(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		String customerStr= HttpServletRequestUtil.getString(request, "customerStr");
		
		ObjectMapper mapper = new ObjectMapper();
		Customer customer = null;
		try {
			customer = mapper.readValue(customerStr, Customer.class);
		} catch (Exception e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		if(customer!=null)
		{
			long customerId = Long.valueOf(HttpServletRequestUtil.getString(request, "customerId"));
			customer.setCustomerId(customerId);
			CustomerExecution ce = customerService.modifyCustomer(customer);
			if(ce.getState()==CustomerStateEnum.SUCCESS.getState())
			{
				modelMap.put("success",true);
			}else
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",ce.getStateInfo());
				
			}
			return modelMap;
		}else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","Please enter the policy information");
			return modelMap;
		}
	}
}
