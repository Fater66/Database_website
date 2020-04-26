package com.fater.wds.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fater.wds.entity.Customer;
import com.fater.wds.service.CustomerService;

@Controller
@RequestMapping("/superadmin")
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/listarea",method = RequestMethod.GET)
	//ResponseBody将返回对象自动转换成json
	@ResponseBody
	private Map<String,Object> listCustomer()
	{
		logger.info("===start===");
		long startTime = System.currentTimeMillis();
		Map<String,Object> modelMap = new HashMap<String, Object>();
		List<Customer> list = new ArrayList<Customer>();
		try {
			list = customerService.getCustomerList();
			modelMap.put("rows",list);
			modelMap.put("total",list.size());
		}catch(Exception e)
		{
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		logger.error("test error!");
		long endTime = System.currentTimeMillis();
		logger.debug("costTime:[{}ms]",endTime -startTime);
		logger.info("===end===");
		return modelMap;
	}
}
