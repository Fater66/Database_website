package com.fater.wds.dao;

import java.util.List;

import com.fater.wds.entity.Payment;

public interface PaymentDao {

	int insertPayment(Payment payment);
	
	List<Payment> queryPaymentListByInvoiceId(long policyId);
}
