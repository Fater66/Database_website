package com.fater.wds.web.driveradmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "driveradmin", method = { RequestMethod.GET })

public class DriverAdminController {
	@RequestMapping(value = "/driveroperation")
	public String driverOperation() {
		return "driver/driveroperation";
	}

	@RequestMapping(value = "/driverlist")
	public String driverList() {
		return "driver/driverlist";
	}
}
