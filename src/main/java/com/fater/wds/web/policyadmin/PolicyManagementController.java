package com.fater.wds.web.policyadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fater.wds.dto.AutoInsuranceExecution;
import com.fater.wds.dto.HomeInsuranceExecution;
import com.fater.wds.dto.HomePolicyExecution;
import com.fater.wds.dto.InvoiceExecution;
import com.fater.wds.dto.PolicyExecution;
import com.fater.wds.dto.VehiclePolicyExecution;
import com.fater.wds.entity.Account;
import com.fater.wds.entity.AutoInsurance;
import com.fater.wds.entity.Customer;
import com.fater.wds.entity.HomeInsurance;
import com.fater.wds.entity.Invoice;
import com.fater.wds.entity.Policy;
import com.fater.wds.enums.AutoInsuranceStateEnum;
import com.fater.wds.enums.HomeInsuranceStateEnum;
import com.fater.wds.enums.HomePolicyStateEnum;
import com.fater.wds.enums.InvoiceStateEnum;
import com.fater.wds.enums.PolicyStateEnum;
import com.fater.wds.enums.VehiclePolicyStateEnum;
import com.fater.wds.service.AutoInsuranceService;
import com.fater.wds.service.HomeInsuranceService;
import com.fater.wds.service.HomePolicyService;
import com.fater.wds.service.InvoiceService;
import com.fater.wds.service.PolicyService;
import com.fater.wds.service.VehiclePolicyService;
import com.fater.wds.util.HttpServletRequestUtil;
import com.fater.wds.util.TimeCalculator;

@Controller
@RequestMapping("/policyadmin")
public class PolicyManagementController {
	@Autowired
	private PolicyService policyService;
	@Autowired
	private HomeInsuranceService homeInsuranceService;
	@Autowired
	private HomePolicyService homePolicyService;
	@Autowired
	private AutoInsuranceService autoInsuranceService;
	@Autowired
	private VehiclePolicyService vehiclePolicyService;
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(value="/getpolicybyid",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getPolicyById(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<String,Object>();
		Long policyId = HttpServletRequestUtil.getLong(request, "policyId");
		if(policyId >1)
		{
			try {
				Policy policy = policyService.getByPolicyId(policyId);
				modelMap.put("success",true);
				modelMap.put("policy",policy);
			} catch (Exception e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
			}
		}else
		{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty policy id");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/getpolicymanagementinfo",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getPolicyManagementInfo(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		long policyId = HttpServletRequestUtil.getLong(request,"policyId");
		//如果是直接访问的就redirect，不是是跳转到选择的policyId的url
		if(policyId<=0)
		{
			Object currentPolicyObj = request.getSession().getAttribute("currentPolicy");
			if(currentPolicyObj == null)
			{
				//redirect
				modelMap.put("redirect",true);
				modelMap.put("url","/wds/policy/policylist");
			}else
			{
				//之前登陆过policy管理页  有权限
				Policy currentPolicy = (Policy)currentPolicyObj;
				modelMap.put("redirect",false);
				modelMap.put("policyId",currentPolicy.getPolicyId());
			}
		}else {
			Policy currentPolicy = new Policy();
			currentPolicy.setPolicyId(policyId);
			request.getSession().setAttribute("currentPolicy", currentPolicy);
			modelMap.put("redirect",false);
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/getpolicylist",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getPolicyList(HttpServletRequest request)
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
			Policy policyCondition = new Policy();
			policyCondition.setCustomer(customer);
			PolicyExecution pe = policyService.getPolicyList(policyCondition);
			modelMap.put("policyList",pe.getPolicyList());
			modelMap.put("customer",customer);
			modelMap.put("success",true);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/searchpolicylist",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> searchPolicyList(HttpServletRequest request)
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
			String policyConditionStr = HttpServletRequestUtil.getString(request, "policyConditionStr");
			Policy policyCondition = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				policyCondition = mapper.readValue(policyConditionStr, Policy.class);
			}catch(Exception e)
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
				return modelMap;
			}
			policyCondition.setCustomer(customer);
			PolicyExecution pe = policyService.getPolicyList(policyCondition);
			modelMap.put("policyList",pe.getPolicyList());
			modelMap.put("customer",customer);
			modelMap.put("success",true);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/registerpolicy",method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	//用户提交的信息都会被保存在httpservletrequest中
	private Map<String,Object> registerPolicy(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		//1.接受前端信息，利用mapper转换为实体类，包括policy信息
		String policyStr = HttpServletRequestUtil.getString(request, "policyStr");
		String durationYearStr = HttpServletRequestUtil.getString(request, "durationYearStr");
		String durationMonthStr = HttpServletRequestUtil.getString(request, "durationMonthStr");
		String installmentStr = HttpServletRequestUtil.getString(request, "installmentStr");
		ObjectMapper mapper = new ObjectMapper();
		Policy policy = null;
		try {
			policy = mapper.readValue(policyStr, Policy.class);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		//2.注册policy
		if(policy!=null)
		{
			Account account = (Account) request.getSession().getAttribute("account");
			policy.setCustomer(account.getCustomer());
			int addmonth = Integer.valueOf(durationYearStr)*12+Integer.valueOf(durationMonthStr);
			policy.setEndDate(TimeCalculator.getPreDoneScore(policy.getStartDate(), addmonth));
			PolicyExecution pe = policyService.addPolicy(policy);
			if(pe.getState() == PolicyStateEnum.CHECK.getState())
			{
				modelMap.put("success", true);
				//policy创建成功 将其加入当前customer的session使之可以操作
				@SuppressWarnings("unchecked")
				List<Policy> policyList= (List<Policy>) request.getSession().getAttribute("policyList");
				if(policyList == null || policyList.size() == 0)
				{
					policyList = new ArrayList<>();
				}
				policyList.add(pe.getPolicy());
				request.getSession().setAttribute("policyList", policyList);
				
				//获取分期选项，0代表不分期
				Integer installment = Integer.valueOf(installmentStr);
				Invoice invoice = new Invoice();
				if(installment ==0)
				{
					invoice.setInvoiceAmount(policy.getPremiumAmount());
					invoice.setInvoiceDate(policy.getStartDate());
					//默认duedate两个月
					invoice.setPaymentDueDate(TimeCalculator.getPreDoneScore(policy.getStartDate(), 2));
					invoice.setPolicyId(policy.getPolicyId());
					InvoiceExecution ie =invoiceService.addInvoice(invoice);
					if(ie.getState() != InvoiceStateEnum.SUCCESS.getState())
					{
						modelMap.put("success", false);
						modelMap.put("errMsg", ie.getStateInfo());
						return modelMap;
					}
				}
				else//分期 添加多个invoice
				{
					Float invoiceamount = policy.getPremiumAmount()/installment;
					invoice.setInvoiceAmount(invoiceamount);
					invoice.setPolicyId(policy.getPolicyId());
					for(int i =0;i<addmonth;i+=installment)
					{
						invoice.setInvoiceDate(TimeCalculator.getPreDoneScore(policy.getStartDate(),i));
						invoice.setPaymentDueDate(TimeCalculator.getPreDoneScore(policy.getStartDate(),i+2));
						InvoiceExecution ie =invoiceService.addInvoice(invoice);
						if(ie.getState() != InvoiceStateEnum.SUCCESS.getState())
						{
							modelMap.put("success", false);
							modelMap.put("errMsg", ie.getStateInfo());
							return modelMap;
						}
					}
				}
				
				//添加insurance 对应关系
				if(policy.getType().equals("H"))
				{
					Long[] homePolicyArray = HttpServletRequestUtil.getLongArray(request, "homePolicyListStr");
					//添加home insurance 信息
					HomeInsurance homeInsurance = new HomeInsurance();
					homeInsurance.setHomeInsuranceType("H");
					homeInsurance.setPolicyId(policy.getPolicyId());
					HomeInsuranceExecution hie = homeInsuranceService.addHomeInsurance(homeInsurance);
					if(hie.getState() != HomeInsuranceStateEnum.SUCCESS.getState())
					{
						modelMap.put("success", false);
						modelMap.put("errMsg", hie.getStateInfo());
						return modelMap;
					}
					//添加home policy信息
					if(homePolicyArray.length >0)
					{
						for(int i =0;i<homePolicyArray.length; i++)
						{
							Long homeId = homePolicyArray[i];
							Long policyId = policy.getPolicyId();
							HomePolicyExecution hpe = homePolicyService.addHomePolicy(homeId, policyId);
							if(hpe.getState() != HomePolicyStateEnum.SUCCESS.getState())
							{
								modelMap.put("success", false);
								modelMap.put("errMsg", hpe.getStateInfo());
								return modelMap;
							}
						}
					}
					else
					{
						
						modelMap.put("success", false);
						modelMap.put("errMsg", "need to select at least one home for home insurance");
						return modelMap;
					}
				}
				else if(policy.getType().equals("A"))
				{
					//添加auto insurance 信息
					Long[] vehiclePolicyArray = HttpServletRequestUtil.getLongArray(request, "vehiclePolicyListStr");
					AutoInsurance autoInsurance = new AutoInsurance();
					autoInsurance.setAutoInsuranceType("A");
					autoInsurance.setPolicyId(policy.getPolicyId());
					AutoInsuranceExecution aie = autoInsuranceService.addAutoInsurance(autoInsurance);
					if(aie.getState() != AutoInsuranceStateEnum.SUCCESS.getState())
					{
						modelMap.put("success", false);
						modelMap.put("errMsg", aie.getStateInfo());
						return modelMap;
					}
					//添加vehicle policy信息
					if(vehiclePolicyArray.length >0)
					{
						for(int i =0;i<vehiclePolicyArray.length; i++)
						{
							Long vehicleId = vehiclePolicyArray[i];
							Long policyId = policy.getPolicyId();
							VehiclePolicyExecution vpe = vehiclePolicyService.addVehiclePolicy(vehicleId, policyId);
							if(vpe.getState() != VehiclePolicyStateEnum.SUCCESS.getState())
							{
								modelMap.put("success", false);
								modelMap.put("errMsg", vpe.getStateInfo());
								return modelMap;
							}
						}
					}
					else
					{
						
						modelMap.put("success", false);
						modelMap.put("errMsg", "need to select at least one vehicle for vehicle insurance");
						return modelMap;
					}
				}
				else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", "need to select at least one home for auto insurance");
					return modelMap;
				}
			}
			else 
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",pe.getStateInfo());
				return modelMap;
			}
			
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","please enter the policy information");
			return modelMap;
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/modifypolicy",method = RequestMethod.POST)
	@ResponseBody
	//用户提交的信息都会被保存在httpservletrequest中
	private Map<String,Object> modifyPolicy(HttpServletRequest request)
	{
		Map<String,Object> modelMap = new HashMap<>();
		//1.接受前端信息，利用mapper转换为实体类，包括policy信息
		String policyStr = HttpServletRequestUtil.getString(request, "policyStr");
		
		ObjectMapper mapper = new ObjectMapper();
		Policy policy = null;
		try {
			policy = mapper.readValue(policyStr, Policy.class);
		}catch(Exception e)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		//2.修改policy信息
		if(policy!=null)
		{
			Customer customer = new Customer();
			//session to do
			long customerId = Long.valueOf(HttpServletRequestUtil.getString(request, "customerId"));
			customer.setCustomerId(customerId);
			policy.setCustomer(customer);
			PolicyExecution pe = policyService.modifyPolicy(policy);
			if(pe.getState() == PolicyStateEnum.CHECK.getState())
			{
				modelMap.put("success", true);
			}
			else
			{
				modelMap.put("success",false);
				modelMap.put("errMsg",pe.getStateInfo());
			}
			return modelMap;
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","please enter the policy information");
			return modelMap;
		}
	}
}
