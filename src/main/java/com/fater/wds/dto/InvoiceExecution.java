package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Invoice;
import com.fater.wds.enums.InvoiceStateEnum;

public class InvoiceExecution {
	//结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	//invoice数量
	private int count;
	
	//操作的invoice（增删改invoice的时候用到)
	private Invoice invoice;
	
	//invoice列表（查询invoice列表的时候使用）
	private List<Invoice> invoiceList;
	public InvoiceExecution() {
		
	}
	//店铺操作失败的构造器
	public InvoiceExecution(InvoiceStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public InvoiceExecution(InvoiceStateEnum stateEnum,Invoice invoice)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.invoice = invoice;
	}
	//操作成功的构造器
	public InvoiceExecution(InvoiceStateEnum stateEnum,List<Invoice> invoiceList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.invoiceList = invoiceList;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	
	
}
