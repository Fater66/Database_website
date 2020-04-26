package com.fater.wds.service;

import com.fater.wds.dto.PaymentExecution;
import com.fater.wds.entity.Payment;

public interface PaymentService {

	public PaymentExecution addPayment(Payment payment);
	
	public PaymentExecution queryPaymentByInvoiceId(long invoiceId);
}
