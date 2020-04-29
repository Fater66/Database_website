package com.fater.wds.web.accountadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fater.wds.dto.AccountExecution;
import com.fater.wds.entity.Account;
import com.fater.wds.entity.Customer;
import com.fater.wds.enums.AccountStateEnum;
import com.fater.wds.service.AccountService;
import com.fater.wds.service.CustomerService;
import com.fater.wds.util.HttpServletRequestUtil;
import com.fater.wds.util.MD5Util;

@Controller
@RequestMapping("/accountadmin")
public class AccountManagementController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/getaccountbyid",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getAccountById(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long accountId = HttpServletRequestUtil.getLong(request, "accountId");
		if(accountId > -1)
		{
			try {
				Account account = accountService.getByAccountId(accountId);
				modelMap.put("account",account);
				modelMap.put("success",true);
			} catch (Exception e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		}
		else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty accountId");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/registeraccount",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerAccount(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		String accountStr = HttpServletRequestUtil.getString(request, "accountStr");
		//session to do 添加customer信息
//		request.getSession().setAttribute("customerId", 1);
		ObjectMapper mapper = new ObjectMapper();
		Account account = null;
		try {
			account = mapper.readValue(accountStr,Account.class);
			account.setPassword(MD5Util.md5(account.getPassword()).substring(0,20));
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		//初始化一个customer
		Customer customer = initCustomer();
		customer.setCustomerZipcode("00000");
		customerService.addCustomer(customer);
		account.setCustomer(customer);
		AccountExecution ae = accountService.addAccount(account);
		if(ae.getState() == AccountStateEnum.CHECK.getState())
		{
			modelMap.put("success",true);
			request.getSession().setAttribute("account", account);
			request.getSession().setAttribute("customer", account.getCustomer());
		}
		else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",ae.getStateInfo());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/initlogin",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> initLogInAccount(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Account account = (Account)request.getSession().getAttribute("account");
		if(account == null)
		{
			modelMap.put("success",false);
		}else
		{
			modelMap.put("success",true);
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/logoutaccount",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> logoutAccount(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		//session to do 添加customer信息
//		request.getSession().setAttribute("customerId", 1);
		
		try {
			request.getSession().setAttribute("account", null);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		modelMap.put("success",true);
		return modelMap;
	}
	
	@RequestMapping(value = "/loginaccount",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> loginAccount(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		String accountStr = HttpServletRequestUtil.getString(request, "accountStr");
		//session to do 添加customer信息
//		request.getSession().setAttribute("customerId", 1);
		ObjectMapper mapper = new ObjectMapper();
		Account account = null;
		try {
			account = mapper.readValue(accountStr,Account.class);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		if(account!=null)
		{
			Account innerAccount = accountService.loginAccount(account.getUsername());
			//密码一致 登陆成功
			String encrypted =MD5Util.md5(account.getPassword()).substring(0,20);
			if(encrypted.equals(innerAccount.getPassword()))
			{
				modelMap.put("success",true);
				Long accountId = innerAccount.getAccountId();
				Account sessionAccount = accountService.getByAccountId(accountId);
				request.getSession().setAttribute("account", sessionAccount);
				request.getSession().setAttribute("customer", account.getCustomer());
			}
			else
			{
				modelMap.put("success",false);
				modelMap.put("errMsg","wrong password");
			}
			return modelMap;
		}
		else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","please enter the account information");
			return modelMap;
		}
	}
	
	private Customer initCustomer()
	{
		Customer customer = new Customer();
		customer.setCustomerLastName("");
		customer.setCustomerFirstName("");
		customer.setCustomerStreetAddress("");
		customer.setCustomerCity("");
		customer.setCustomerState("");
		customer.setCustomerZipcode("");
		customer.setCustomerMaritalStatus("M");
		customer.setCustomerGender("M");
		return customer;
	}
}
