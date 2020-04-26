/**
 * 
 */

$(function(){
	var policyId = getQueryString('policyId');
	
	getlist();
	function getlist(e){
		$.ajax({
			url:"/wds/invoiceadmin/getinvoicelistbypolicyid?policyId="+policyId,
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handleList(data.invoiceList);
				}
			}
		});
	}
	
	function handleList(data){
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-20">'
				+ item.invoiceId + '</div><div class="col-20">'
				+ item.invoiceDate
				+ '</div><div class="col-20">'
				+ item.paymentDueDate
				+ '</div><div class="col-20">'
				+ item.invoiceAmount
				+ '</div><div class="col-10">'
				+ goPayment(item.invoiceId)
				+ '</div></div>';
		});
		$('.invoice-wrap').html(html);
	}
	
	function goPayment(id){
		return '<a href="/wds/paymentadmin/paymentlist?invoiceId='+id+'">View</a>';
	}
})
	