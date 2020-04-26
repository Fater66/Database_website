package com.fater.wds.web.homeadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "homeadmin",method= {RequestMethod.GET})
public class HomeAdminController {
	
	@RequestMapping(value="/homeoperation")
	public String homeOperation()
	{
		return "home/homeoperation";
	}
	
	@RequestMapping(value="/homelist")
	public String homeList()
	{
		return "home/homelist";
	}
}
