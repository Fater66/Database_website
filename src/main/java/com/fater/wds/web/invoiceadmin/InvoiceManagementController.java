package com.fater.wds.web.invoiceadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fater.wds.dto.InvoiceExecution;
import com.fater.wds.service.InvoiceService;
import com.fater.wds.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/invoiceadmin")
public class InvoiceManagementController {

	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(value = "/getinvoicelistbypolicyid",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getInvoiceListByPolicyId(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long policyId = HttpServletRequestUtil.getLong(request, "policyId");
		if(policyId >1)
		{
			try {
				InvoiceExecution ie = invoiceService.queryInvoiceByPolicyId(policyId);
				modelMap.put("invoiceList",ie.getInvoiceList());
				modelMap.put("success",true);
		}catch(Exception e)
			{	
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			}
		}
		else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty policy id");
		}
		return modelMap;
	}
}
