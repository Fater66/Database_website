package com.fater.wds.web.invoiceadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "invoiceadmin",method= {RequestMethod.GET})
public class InvoiceAdminController {
	
	@RequestMapping(value="/invoiceoperation")
	public String invoiceOperation()
	{
		return "invoice/invoiceoperation";
	}
	
	@RequestMapping(value="/invoicelist")
	public String invoiceList()
	{
		return "invoice/invoicelist";
	}
}
