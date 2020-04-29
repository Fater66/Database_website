package com.fater.wds.service;

import com.fater.wds.dto.AdministraterExecution;
import com.fater.wds.entity.Administrater;

public interface AdministraterService {
	AdministraterExecution addAdministrater(Administrater administrater);
	Administrater loginAdministrater(String administraterUsername);
	Administrater getByAdministraterId(long administraterId);
}
