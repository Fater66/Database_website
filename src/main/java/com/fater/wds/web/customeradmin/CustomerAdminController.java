package com.fater.wds.web.customeradmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "customeradmin",method = RequestMethod.GET)

public class CustomerAdminController {

	@RequestMapping(value="/customeroperation")
	public String customerOperation()
	{
		return "customer/customeroperation";
	}
}
