/**
 * 
 */
/**;
 * 
 */

$(function(){
	getlist();
	var customerId = getQueryString('customerId');
	var filterView = document.getElementById('filter-view');
	filterView.style.display="none";
	function getlist(e){
		$.ajax({
			url:"/wds/homeadmin/gethomelist",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handleList(data.homeList);
					//渲染用户名
					handleCustomer(data.customer);
				}
			}
		});
	}
	
	$('#search').click(function()
			{
				var homeCondition = {};
				homeCondition.customerId = customerId;
				var formData = new FormData();
				formData.append('minValueStr',$('#min-value').val());
				formData.append('maxValueStr',$('#max-value').val());
				formData.append('minDateStr',$('#min-date').val());
				formData.append('maxDateStr',$('#max-date').val());
				formData.append('minAreaStr',$('#min-area').val());
				formData.append('maxAreaStr',$('#max-area').val());
				formData.append('homeConditionStr',JSON.stringify(homeCondition));
				$.ajax({
					url:("/wds/homeadmin/searchhomelist"),
					type:'POST',
					data:formData,
					contentType:false,
					processData:false,
					cache:false,
					success:function(data){
						if(data.success){
							handleList(data.homeList);
						}else{
							$.toast('search fail'+data.errMsg);
						}
					}
				});
			});
	
	function handleModifyCustomer(data)
	{
		document.getElementById("modify-customer").href = "/wds/customeradmin/customeroperation?customerId="+data.customerId;
	}
	
	function handleHomeOperation(data)
	{
		document.getElementById("home-operation").href = "/wds/homeadmin/homelist?customerId="+data.customerId;
	}
	
	function handleCustomer(data){
		$('#customer-name').text(data.customerFirstName +" " +data.customerLastName + ",UID:" + data.customerId);
	}
	
	function handleList(data){
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-20">'
				+ item.homeId + '</div><div class="col-20">'
				+ item.homePurchaseDate
				+ '</div><div class="col-20">'
				+ item.homePurchaseValue
				+ '</div><div class="col-20">'
				+ item.homeArea
				+ '</div><div class="col-20">'
				+ goHome(item.homeId) 
				+ '</div></div>';
		});
		$('.home-wrap').html(html);
	}
	
	
	function statusTransfer(status,id)
	{
		if(status == "P")
			return "Paid";
		else return '<a href="/wds/paymentadmin/paymentoperation?policyId='+id+'">Pay</a>';
	}
	function goHome(id){
		return '<a href="/wds/homeadmin/homeoperation?homeId='+id+'">Modify</a>';
	}
})
	