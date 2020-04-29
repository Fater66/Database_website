package com.fater.wds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fater.wds.dao.AdministraterDao;
import com.fater.wds.dto.AdministraterExecution;
import com.fater.wds.entity.Administrater;
import com.fater.wds.enums.AdministraterStateEnum;
import com.fater.wds.exceptions.AdministraterOperationException;
import com.fater.wds.service.AdministraterService;

@Service
public class AdministraterServiceImpl implements AdministraterService{

	@Autowired
	private AdministraterDao administraterDao;
	@Override
	public AdministraterExecution addAdministrater(Administrater administrater) {
		if(administrater==null)
		{
			return new AdministraterExecution(AdministraterStateEnum.NULL_ADMINISTRATER);
		}
		try
		{
			int effectedNum = administraterDao.insertAdministrater(administrater);
			if(effectedNum < 0)
			{
				throw new AdministraterOperationException("fail to add administrater");
			}
		}catch (Exception e)
		{
			throw new AdministraterOperationException("add administrater error" + e.getMessage());
		}
		return new AdministraterExecution(AdministraterStateEnum.CHECK,administrater);
	}
	
	@Override
	public Administrater getByAdministraterId(long administraterId) {
		return administraterDao.queryByAdministraterId(administraterId);
	}

	@Override
	public Administrater loginAdministrater(String administraterUsername) {
		return administraterDao.queryByAdministraterUsername(administraterUsername);
	}
	
}
