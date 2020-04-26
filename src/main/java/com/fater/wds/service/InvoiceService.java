package com.fater.wds.service;

import com.fater.wds.dto.InvoiceExecution;
import com.fater.wds.entity.Invoice;

public interface InvoiceService {

	public InvoiceExecution addInvoice(Invoice invoice);
	
	public InvoiceExecution queryInvoiceByPolicyId(long policyId);
}
