package com.fater.wds.web.homeadmin;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fater.wds.dto.HomeExecution;
import com.fater.wds.dto.PolicyExecution;
import com.fater.wds.entity.Account;
import com.fater.wds.entity.Customer;
import com.fater.wds.entity.Home;
import com.fater.wds.entity.Policy;
import com.fater.wds.enums.HomeStateEnum;
import com.fater.wds.service.HomeService;
import com.fater.wds.util.HttpServletRequestUtil;


@Controller
@RequestMapping("/homeadmin")
public class HomeManagementController {

	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "/gethomelistbypolicyid",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getHomeListByPolicyId(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long policyId = HttpServletRequestUtil.getLong(request, "policyId");
		if(policyId >1)
		{
			try {
				HomeExecution he = homeService.queryHomeByPolicyId(policyId);
				modelMap.put("homeList",he.getHomeList());
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
	
	@RequestMapping(value = "/gethomelist",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getHomeList(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		
		Account account = (Account)request.getSession().getAttribute("account");
		if(account == null)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg","need to log in");
			return modelMap;
		}
		if(account.getCustomer() == null)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg","need to create customer information");
			return modelMap;
		}
		Customer customer = account.getCustomer();
		
		try {
			HomeExecution he = homeService.queryHomeByCustomerId(customer.getCustomerId());
			modelMap.put("homeList",he.getHomeList());
			modelMap.put("customer",customer);
			modelMap.put("success",true);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/searchhomelist",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> searchHomeList(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		
		Account account = (Account)request.getSession().getAttribute("account");
		if(account == null)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg","need to log in");
			return modelMap;
		}
		if(account.getCustomer() == null)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg","need to create customer information");
			return modelMap;
		}
		Customer customer = account.getCustomer();
		
		try {
			String homeConditionStr = HttpServletRequestUtil.getString(request, "homeConditionStr");
			Date minDate = null;
			if(HttpServletRequestUtil.getString(request, "minDateStr")!=null)
			{
				minDate = Date.valueOf(HttpServletRequestUtil.getString(request, "minDateStr"));
			}
			Date maxDate = null;
			if(HttpServletRequestUtil.getString(request, "maxDateStr")!=null)
			{
				maxDate = Date.valueOf(HttpServletRequestUtil.getString(request, "maxDateStr"));
			}
			Float minValue = null;
			if(HttpServletRequestUtil.getString(request, "minValueStr")!=null)
			{
				minValue = Float.valueOf(HttpServletRequestUtil.getString(request, "minValueStr"));
			}
			Float maxValue = null;
			if(HttpServletRequestUtil.getString(request, "maxValueStr")!=null)
			{
				maxValue = Float.valueOf(HttpServletRequestUtil.getString(request, "maxValueStr"));
			}
			Float minArea = null;
			if(HttpServletRequestUtil.getString(request, "minAreaStr")!=null)
			{
				minArea = Float.valueOf(HttpServletRequestUtil.getString(request, "minAreaStr"));
			}
			Float maxArea = null;
			if(HttpServletRequestUtil.getString(request, "maxAreaStr")!=null)
			{
				maxArea = Float.valueOf(HttpServletRequestUtil.getString(request, "maxAreaStr"));
			}
			Home homeCondition = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				homeCondition = mapper.readValue(homeConditionStr, Home.class);
			}catch(Exception e)
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
				return modelMap;
			}
			homeCondition.setCustomerId(customer.getCustomerId());
			
			HomeExecution he = homeService.getHomeList(homeCondition, minDate, maxDate, minValue, maxValue, minArea, maxArea);
			modelMap.put("homeList",he.getHomeList());
			modelMap.put("customer",customer);
			modelMap.put("success",true);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/gethomebyid",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getHomeById(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<String,Object>();
		Long homeId = HttpServletRequestUtil.getLong(request, "homeId");
		if(homeId >1)
		{
			try {
				Home home = homeService.getByHomeId(homeId);
				modelMap.put("success",true);
				modelMap.put("home",home);
			} catch (Exception e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
			}
		}else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty home id");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/modifyhome",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> modifyHome(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		String homeStr= HttpServletRequestUtil.getString(request, "homeStr");
		
		ObjectMapper mapper = new ObjectMapper();
		Home home = null;
		try {
			home = mapper.readValue(homeStr, Home.class);
		} catch (Exception e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		if(home!=null)
		{
			long homeId = Long.valueOf(HttpServletRequestUtil.getString(request, "homeId"));
			home.setHomeId(homeId);
			HomeExecution he = homeService.modifyHome(home);
			if(he.getState()==HomeStateEnum.SUCCESS.getState())
			{
				modelMap.put("success",true);
			}else
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",he.getStateInfo());
				
			}
			return modelMap;
		}else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","Please enter the home information");
			return modelMap;
		}
	}
	
	@RequestMapping(value = "/registerhome",method = RequestMethod.POST)
	@ResponseBody
	//用户提交的信息都会被保存在httpservletrequest中
	private Map<String,Object> registerHome(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		//1.接受前端信息，利用mapper转换为实体类，包括home信息
		String homeStr = HttpServletRequestUtil.getString(request, "homeStr");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Home home = null;
		try {
			home = mapper.readValue(homeStr, Home.class);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		//2.注册home
		if(home!=null)
		{
			Account account = (Account) request.getSession().getAttribute("account");
			home.setCustomerId(account.getCustomer().getCustomerId());
			//session to do
			HomeExecution he = homeService.addHome(home);
			if(he.getState() == HomeStateEnum.SUCCESS.getState())
			{
				modelMap.put("success", true);
			}
			else 
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",he.getStateInfo());
			}
			return modelMap;
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","please enter the home information");
			return modelMap;
		}
	}
}
