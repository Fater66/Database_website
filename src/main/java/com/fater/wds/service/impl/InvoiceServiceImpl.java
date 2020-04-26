package com.fater.wds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.InvoiceDao;
import com.fater.wds.dto.InvoiceExecution;
import com.fater.wds.entity.Invoice;
import com.fater.wds.enums.InvoiceStateEnum;
import com.fater.wds.exceptions.InvoiceOperationException;
import com.fater.wds.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	@Autowired
	private InvoiceDao invoiceDao;

	@Override
	@Transactional
	public InvoiceExecution addInvoice(Invoice invoice) {
		if(invoice == null)
		{
			return new InvoiceExecution(InvoiceStateEnum.NULL_INVOICE);
		}
		try
		{
			int effectedNum = invoiceDao.insertInvoice(invoice);
			if(effectedNum<=0)
			{
				throw new InvoiceOperationException("fail to add invoice");
			}
		}catch (Exception e)
		{
			throw new InvoiceOperationException("add invoice error" + e.getMessage());
		}
		return new InvoiceExecution(InvoiceStateEnum.SUCCESS,invoice);
	}

	@Override
	public InvoiceExecution queryInvoiceByPolicyId(long policyId) {
		List<Invoice> invoiceList=invoiceDao.queryInvoiceListByPolicyId(policyId);
		InvoiceExecution he = new InvoiceExecution();
		if(invoiceList != null)
		{
			he.setInvoiceList(invoiceList);
			he.setCount(invoiceList.size());
		}else
		{
			he.setState(InvoiceStateEnum.INNER_ERROR.getState());
		}
		return he;
	}
}
