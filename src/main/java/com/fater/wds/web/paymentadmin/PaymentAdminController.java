package com.fater.wds.web.paymentadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "paymentadmin",method= {RequestMethod.GET})
public class PaymentAdminController {
	
	@RequestMapping(value="/paymentoperation")
	public String paymentOperation()
	{
		return "payment/paymentoperation";
	}
	
	@RequestMapping(value="/paymentlist")
	public String paymentList()
	{
		return "payment/paymentlist";
	}
}
