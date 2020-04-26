package com.fater.wds.web.policyadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "policyadmin",method= {RequestMethod.GET})
public class PolicyAdminController {
	
	@RequestMapping(value="/policyoperation")
	public String policyOperation()
	{
		return "policy/policyoperation";
	}
	
	@RequestMapping(value="/policylist")
	public String policyList()
	{
		return "policy/policylist";
	}
}
