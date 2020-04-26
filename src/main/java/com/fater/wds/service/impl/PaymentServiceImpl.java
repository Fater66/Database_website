package com.fater.wds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.PaymentDao;
import com.fater.wds.dto.PaymentExecution;
import com.fater.wds.entity.Payment;
import com.fater.wds.enums.PaymentStateEnum;
import com.fater.wds.exceptions.PaymentOperationException;
import com.fater.wds.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentDao paymentDao;

	@Override
	@Transactional
	public PaymentExecution addPayment(Payment payment) {
		if(payment == null)
		{
			return new PaymentExecution(PaymentStateEnum.NULL_PAYMENT);
		}
		try
		{
			int effectedNum = paymentDao.insertPayment(payment);
			if(effectedNum<=0)
			{
				throw new PaymentOperationException("fail to add payment");
			}
		}catch (Exception e)
		{
			throw new PaymentOperationException("add payment error" + e.getMessage());
		}
		return new PaymentExecution(PaymentStateEnum.SUCCESS,payment);
	}

	@Override
	public PaymentExecution queryPaymentByInvoiceId(long invoiceId) {
		List<Payment> paymentList=paymentDao.queryPaymentListByInvoiceId(invoiceId);
		PaymentExecution he = new PaymentExecution();
		if(paymentList != null)
		{
			he.setPaymentList(paymentList);
			he.setCount(paymentList.size());
		}else
		{
			he.setState(PaymentStateEnum.INNER_ERROR.getState());
		}
		return he;
	}
}
