package com.fater.wds.web.customeradmin;

import java.util.HashMap;
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
