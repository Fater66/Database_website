/**;
 * 
 */

$(function(){
	//获取policy list方法
	getlist();
	var filterView = document.getElementById('filter-view');
	filterView.style.display="none";
	
	function getlist(e){
		$.ajax({
			url:"/wds/policyadmin/getpolicylist",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handleList(data.policyList);
					//渲染用户名
					handleCustomer(data.customer);
					handleModifyCustomer(data.customer);
					handleHomeOperation(data.customer);
					handleVehicleOperation(data.customer);
					handleDriverOperation(data.customer);
				}
			}
		});
	}
	
	
	$('#search').click(function()
	{
		var policyCondition = {};
		policyCondition.type = ($('#type-search').val() == 'Auto Insurance')?'A':'H';
		policyCondition.startDate = $('#start-date-search').val();
		policyCondition.endDate = $('#end-date-search').val();
		policyCondition.premiumAmount = $('#premium-amount-search').val();
		var formData = new FormData();
		formData.append('policyConditionStr',JSON.stringify(policyCondition));
		
		$.ajax({
			url:("/wds/policyadmin/searchpolicylist"),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					handleList(data.policyList);
				}else{
					$.toast('search fail'+data.errMsg);
				}
			}
		});
	});
	
	function handleList(data){
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-25">'
				+ typeTransfer(item.type) + '</div><div class="col-20">'
				+ item.startDate
				+ '</div><div class="col-20">'
				+ item.endDate
				+ '</div><div class="col-20">'
				+ goPolicy(item.policyId)  
				+ '</div><div class="col-10">'
				+ statusTransfer(item.status,item.policyId)
				+ '</div></div>';
		});
		$('.policy-wrap').html(html);
	}
	
	function typeTransfer(type)
	{
		if(type == "A")
			return "Auto Insurance";
		else return "Home Insurance";
	}
	
	function statusTransfer(status,id)
	{
		if(status == "P")
			return "Paid";
		else return '<a href="/wds/invoiceadmin/invoicelist?policyId='+id+'">Pay</a>';
	}
	function goPolicy(id){
		return '<a href="/wds/policyadmin/policyoperation?policyId='+id+'">View</a>';
	}
	
	function handleModifyCustomer(data)
	{
		document.getElementById("modify-customer").href = "/wds/customeradmin/customeroperation?customerId="+data.customerId;
	}
	
	function handleHomeOperation(data)
	{
		document.getElementById("home-operation").href = "/wds/homeadmin/homelist?customerId="+data.customerId;
	}
	
	function handleVehicleOperation(data)
	{
		document.getElementById("vehicle-operation").href = "/wds/vehicleadmin/vehiclelist?customerId="+data.customerId;
	}
	
	function handleDriverOperation(data)
	{
		document.getElementById("driver-operation").href = "/wds/driveradmin/driverlist?customerId="+data.customerId;
	}
	
	function handleCustomer(data){
		$('#customer-name').text(data.customerFirstName +" " +data.customerLastName + ",UID:" + data.customerId);
	}
})