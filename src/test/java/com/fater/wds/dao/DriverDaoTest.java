package com.fater.wds.dao;

import java.sql.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Driver;

public class DriverDaoTest extends BaseTest{
	@Autowired
	private DriverDao driverDao;
	
	@Test
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

}
