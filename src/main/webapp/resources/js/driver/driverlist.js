/**
 * 
 */
/**;
 * 
 */

$(function(){
	getlist();
	function getlist(e){
		$.ajax({
			url:"/wds/driveradmin/getdriverlist",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handleList(data.driverList);
					//渲染用户名
					handleCustomer(data.customer);
				}
			}
		});
	}
	
	function handleModifyCustomer(data)
	{
		document.getElementById("modify-customer").href = "/wds/customeradmin/customeroperation?customerId="+data.customerId;
	}
	
	function handleCustomer(data){
		$('#customer-name').text(data.customerFirstName +" " +data.customerLastName + ",UID:" + data.customerId);
	}
	
	function handleList(data){
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-20">'
				+ item.driverLicenseId + '</div><div class="col-20">'
				+ item.driverLastName
				+ '</div><div class="col-20">'
				+ item.driverFirstName
				+ '</div><div class="col-20">'
				+ item.driverBirthDate
				+ '</div><div class="col-10">'
				+ goDriver(item.driverId) 
				+ '</div></div>';
		});
		$('.driver-wrap').html(html);
	}

	function goDriver(id){
		return '<a href="/wds/driveradmin/driveroperation?driverId='+id+'">Modify</a>';
	}
})
	