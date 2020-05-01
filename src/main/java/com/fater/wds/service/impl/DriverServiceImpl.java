package com.fater.wds.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.DriverDao;
import com.fater.wds.dto.DriverExecution;
import com.fater.wds.entity.Driver;
import com.fater.wds.enums.DriverStateEnum;
import com.fater.wds.exceptions.DriverOperationException;
import com.fater.wds.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverDao driverDao;

	@Override
	public DriverExecution queryDriverByVehicleId(long vehicleId) {
		List<Driver> driverList=driverDao.queryDriverListByVehicleId(vehicleId);
		DriverExecution de = new DriverExecution();
		if(driverList != null)
		{
			de.setDriverList(driverList);
			de.setCount(driverList.size());
		}else
		{
			de.setState(DriverStateEnum.INNER_ERROR.getState());
		}
		return de;
	}
	
	@Override
	@Transactional
	public DriverExecution addDriver(Driver driver) {
		if (driver == null) {
			return new DriverExecution(DriverStateEnum.NULL_DRIVER);
		}
		try {
			int effectedNum = driverDao.insertDriver(driver);
			if (effectedNum <= 0) {
				throw new DriverOperationException("fail to add driver");
			}
		} catch (Exception e) {
			throw new DriverOperationException("add driver error" + e.getMessage());
		}
		return new DriverExecution(DriverStateEnum.SUCCESS, driver);
	}

	@Override
	public DriverExecution queryDriverByCustomerId(long customerId) {
		List<Driver> driverList = driverDao.queryDriverListByCustomerId(customerId);
		DriverExecution he = new DriverExecution();
		if (driverList != null) {
			he.setDriverList(driverList);
			he.setCount(driverList.size());
		} else {
			he.setState(DriverStateEnum.INNER_ERROR.getState());
		}
		return he;
	}

	@Override
	public Driver getByDriverId(long driverId) {
		return driverDao.queryByDriverId(driverId);
	}

	@Override
	public DriverExecution modifyDriver(Driver driver) {
		if (driver == null) {
			return new DriverExecution(DriverStateEnum.NULL_DRIVER);
		} else {
			try {
				int effectedNum = driverDao.updateDriver(driver);
				if (effectedNum <= 0) {
					return new DriverExecution(DriverStateEnum.INNER_ERROR);
				} else {
					driver = driverDao.queryByDriverId(driver.getDriverId());
					return new DriverExecution(DriverStateEnum.SUCCESS, driver);
				}
			} catch (Exception e) {
				throw new DriverOperationException("Modify driver error" + e.getMessage());
			}
		}
	}

	@Override
	public DriverExecution getDriverList(Driver driverCondition, Date minDate, Date maxDate) {
		List<Driver> driverList=driverDao.queryDriverListByCondition(driverCondition, minDate, maxDate);
		DriverExecution he = new DriverExecution();
		if(driverList != null)
		{
			he.setDriverList(driverList);
		}else
		{
			he.setState(DriverStateEnum.INNER_ERROR.getState());
		}
		return he;
	}
	
	@Override
	public DriverExecution deleteDriver(long driverId) {
		try {
			int effectedNum = driverDao.deleteDriver(driverId);
			if(effectedNum <=0)
			{
				return new DriverExecution(DriverStateEnum.INNER_ERROR);
			}
			else
			{
				return new DriverExecution(DriverStateEnum.SUCCESS);
			}
		}catch(Exception e)
		{
			throw new DriverOperationException("Delete driver error" + e.getMessage());
		}

	}

	@Override
	public List<Driver> getAllDriverList()
	{
		return driverDao.queryAllDriver();
	}
	
}
