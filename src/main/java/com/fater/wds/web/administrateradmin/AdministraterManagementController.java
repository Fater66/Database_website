package com.fater.wds.web.administrateradmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fater.wds.entity.Administrater;
import com.fater.wds.service.AdministraterService;
import com.fater.wds.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/administrateradmin")
public class AdministraterManagementController {
	
	@Autowired
	private AdministraterService administraterService;
	

	
	@RequestMapping(value = "/getadministraterbyid",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getAdministraterById(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Long administraterId = HttpServletRequestUtil.getLong(request, "administraterId");
		if(administraterId > -1)
		{
			try {
				Administrater administrater = administraterService.getByAdministraterId(administraterId);
				modelMap.put("administrater",administrater);
				modelMap.put("success",true);
			} catch (Exception e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		}
		else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty administraterId");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/initlogin",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> initLogInAdministrater(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		Administrater administrater = (Administrater)request.getSession().getAttribute("administrater");
		if(administrater == null)
		{
			modelMap.put("success",false);
		}else
		{
			modelMap.put("success",true);
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/logoutadministrater",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> logoutAdministrater(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		//session to do 添加customer信息
//		request.getSession().setAttribute("customerId", 1);
		
		try {
			request.getSession().setAttribute("administrater", null);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		modelMap.put("success",true);
		return modelMap;
	}
	
	@RequestMapping(value = "/loginadministrater",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> loginAdministrater(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		String administraterStr = HttpServletRequestUtil.getString(request, "administraterStr");
		//session to do 添加customer信息
//		request.getSession().setAttribute("customerId", 1);
		ObjectMapper mapper = new ObjectMapper();
		Administrater administrater = null;
		try {
			administrater = mapper.readValue(administraterStr,Administrater.class);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		if(administrater!=null)
		{
			Administrater innerAdministrater = administraterService.loginAdministrater(administrater.getUsername());
			//密码一致 登陆成功
//			String encrypted =MD5Util.md5(administrater.getPassword()).substring(0,20);
			if(administrater.getPassword().equals(innerAdministrater.getPassword()))
			{
				modelMap.put("success",true);
				Long administraterId = innerAdministrater.getAdministraterId();
				Administrater sessionAdministrater = administraterService.getByAdministraterId(administraterId);
				request.getSession().setAttribute("administrater", sessionAdministrater);
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
			modelMap.put("errMsg","please enter the administrater information");
			return modelMap;
		}
	}
}
