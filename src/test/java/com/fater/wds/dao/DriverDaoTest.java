package com.fater.wds.dao;

import java.sql.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Driver;

public class DriverDaoTest extends BaseTest{
	@Autowired
	private DriverDao driverDao;
	
	@Test
	@Ignore
	public void testInsertDriver()
	{
		Driver driver = new Driver();
		driver.setDriverLicenseId("CLINTFJ273J9");
		driver.setDriverBirthDate(Date.valueOf("1996-12-17"));
		driver.setDriverFirstName("Yutong");
		driver.setDriverLastName("Feng");
		driver.setCustomerId(4L);
		driverDao.insertDriver(driver);
	}

	@Test
	public void testQueryDriverListByCondition()
	{
		Driver driverCondition = new Driver();
		driverCondition.setCustomerId(4L);
		driverCondition.setDriverLastName("Yilu");
		List<Driver> driverList = driverDao.queryDriverListByCondition(driverCondition, null, null);
		for(int i =0;i<driverList.size();i++)
		{
			System.out.println(driverList.get(i).getDriverFirstName());
		}
		
	}
}
