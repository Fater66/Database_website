package com.fater.wds.web.paymentadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fater.wds.dto.PaymentExecution;
import com.fater.wds.entity.Account;
import com.fater.wds.entity.Payment;
import com.fater.wds.enums.PaymentStateEnum;
import com.fater.wds.service.PaymentService;
import com.fater.wds.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/paymentadmin")
public class PaymentManagementController {

	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "/getpaymentlistbyinvoiceid",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getPaymentListByInvoiceId(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long invoiceId = HttpServletRequestUtil.getLong(request, "invoiceId");
		if(invoiceId >1)
		{
			try {
				PaymentExecution pe = paymentService.queryPaymentByInvoiceId(invoiceId);
				modelMap.put("paymentList",pe.getPaymentList());
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
			modelMap.put("errMsg","empty invoice id");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/registerpayment", method = RequestMethod.POST)
	@ResponseBody
	//用户提交的信息都会被保存在httpservletrequest中
	private Map<String, Object> registerPayment(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		//1.接受前端信息，利用mapper转换为实体类，包括payment信息
		String paymentStr = HttpServletRequestUtil.getString(request, "paymentStr");
		
		ObjectMapper mapper = new ObjectMapper();
		Payment payment = null;
		try {
			payment = mapper.readValue(paymentStr, Payment.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//2.注册payment
		if (payment != null) {
			Account account = (Account) request.getSession().getAttribute("account");
			if(account == null)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", "Login in expired");
				return modelMap;
			}
			payment.setCustomerId(account.getCustomer().getCustomerId());
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			payment.setPaymentDate(currentDate);
			//session to do
			PaymentExecution pe = paymentService.addPayment(payment);
			if (pe.getState() == PaymentStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", pe.getStateInfo());
				return modelMap;
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please enter the payment information");
			return modelMap;
		}
	}
}
