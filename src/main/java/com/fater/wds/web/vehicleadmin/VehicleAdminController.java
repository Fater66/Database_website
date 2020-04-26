package com.fater.wds.web.vehicleadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "vehicleadmin", method = { RequestMethod.GET })

public class VehicleAdminController {
	@RequestMapping(value = "/vehicleoperation")
	public String vehicleOperation() {
		return "vehicle/vehicleoperation";
	}

	@RequestMapping(value = "/vehiclelist")
	public String vehicleList() {
		return "vehicle/vehiclelist";
	}
}
