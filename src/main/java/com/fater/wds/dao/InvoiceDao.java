package com.fater.wds.dao;

import java.util.List;

import com.fater.wds.entity.Invoice;

public interface InvoiceDao {
	int insertInvoice(Invoice invoice);
	
	List<Invoice> queryInvoiceListByPolicyId(long policyId);
}
