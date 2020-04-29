package com.fater.wds.web.administrateradmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//用于前端与后端的mapping  比如输入administrateradmin/administrateroperation 就会跳到administrater/administrateroperation
//requestmapping value是输入 return的是web-inf内的网址
@Controller
@RequestMapping(value = "administrateradmin",method = RequestMethod.GET)
public class AdministraterAdminController {
	
	@RequestMapping(value="/administrateroperation")
	public String administraterOperation()
	{
		return "administrater/administrateroperation";
	}
}
