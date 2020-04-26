package com.fater.wds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.HomeDao;
import com.fater.wds.dto.HomeExecution;
import com.fater.wds.entity.Home;
import com.fater.wds.enums.HomeStateEnum;
import com.fater.wds.exceptions.HomeOperationException;
import com.fater.wds.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService{

	@Autowired
	private HomeDao homeDao;
	
	@Override
	@Transactional
	public HomeExecution addHome(Home home) {
		if(home == null)
		{
			return new HomeExecution(HomeStateEnum.NULL_HOME);
		}
		try
		{
			int effectedNum = homeDao.insertHome(home);
			if(effectedNum<=0)
			{
				throw new HomeOperationException("fail to add home");
			}
		}catch (Exception e)
		{
			throw new HomeOperationException("add home error" + e.getMessage());
		}
		return new HomeExecution(HomeStateEnum.SUCCESS,home);
	}

	@Override
	public HomeExecution queryHomeByCustomerId(long customerId) {
		List<Home> homeList=homeDao.queryHomeListByCustomerId(customerId);
		HomeExecution he = new HomeExecution();
		if(homeList != null)
		{
			he.setHomeList(homeList);
			he.setCount(homeList.size());
		}else
		{
			he.setState(HomeStateEnum.INNER_ERROR.getState());
		}
		return he;
	}

	@Override
	public HomeExecution queryHomeByPolicyId(long policyId) {
		List<Home> homeList=homeDao.queryHomeListByPolicyId(policyId);
		HomeExecution he = new HomeExecution();
		if(homeList != null)
		{
			he.setHomeList(homeList);
			he.setCount(homeList.size());
		}else
		{
			he.setState(HomeStateEnum.INNER_ERROR.getState());
		}
		return he;
	}
	
	@Override
	public Home getByHomeId(long homeId) {
		return homeDao.queryByHomeId(homeId);
	}

	@Override
	public HomeExecution modifyHome(Home home) {
		if(home ==null)
		{
			return new HomeExecution(HomeStateEnum.NULL_HOME);
		}
		else
		{
			try {
				int effectedNum = homeDao.updateHome(home);
				if(effectedNum <=0)
				{
					return new HomeExecution(HomeStateEnum.INNER_ERROR);
				}
				else
				{
					home = homeDao.queryByHomeId(home.getHomeId());
					return new HomeExecution(HomeStateEnum.SUCCESS,home);
				}
			}catch(Exception e)
			{
				throw new HomeOperationException("Modify home error" + e.getMessage());
			}
		}
	}

}
