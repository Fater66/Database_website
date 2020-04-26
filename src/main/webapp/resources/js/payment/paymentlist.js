/**
 * 
 */

$(function(){
	var invoiceId = getQueryString('invoiceId');
	
	getlist();
	handlePayment(invoiceId);
	function getlist(e){
		$.ajax({
			url:"/wds/paymentadmin/getpaymentlistbyinvoiceid?invoiceId="+invoiceId,
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handleList(data.paymentList);
					//
					
				}
			}
		});
	}
	
	function handleList(data){
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-20">'
				+ item.paymentId + '</div><div class="col-20">'
				+ item.paymentDate
				+ '</div><div class="col-20">'
				+ item.paymentMethod
				+ '</div><div class="col-20">'
				+ item.paymentAmount
				+ '</div></div>';
		});
		$('.payment-wrap').html(html);
	}
	
	function handlePayment(id)
	{
		document.getElementById("make-payment").href = "/wds/paymentadmin/paymentoperation?invoiceId="+invoiceId;
	}
})
	