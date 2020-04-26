package com.fater.wds.web.accountadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//用于前端与后端的mapping  比如输入accountadmin/accountoperation 就会跳到account/accountoperation
//requestmapping value是输入 return的是web-inf内的网址
@Controller
@RequestMapping(value = "accountadmin",method = RequestMethod.GET)
public class AccountAdminController {
	
	@RequestMapping(value="/accountoperation")
	public String accountOperation()
	{
		return "account/accountoperation";
	}
}
