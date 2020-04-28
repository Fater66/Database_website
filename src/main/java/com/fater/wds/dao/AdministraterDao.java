package com.fater.wds.dao;

import com.fater.wds.entity.Administrater;

public interface AdministraterDao {

	Administrater queryByAdministraterId(long administraterId);
	
	int insertAdministrater(Administrater administrater);
	
	Administrater queryByAdministraterUsername(String username);
}
