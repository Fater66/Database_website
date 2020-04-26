/**
 * 
 */
$(function(){
	var invoiceId = getQueryString('invoiceId');
	var paymentId = getQueryString('paymentId');
	// 由于店铺注册和编辑使用的是同一个页面，
	// 该标识符用来标明本次是添加还是编辑操作
	var isEdit = paymentId ? true : false;
	// 注册home的URL
	var registerPaymentUrl = '/wds/paymentadmin/registerpayment';
	 
	
	 $('#pay').click(function(){
		var payment ={};
		payment.invoiceId = invoiceId;
		payment.paymentAmount=$('#payment-amount').val();
		payment.paymentMethod=$('#payment-method').val();
		
		var formData = new FormData();
		formData.append('paymentStr',JSON.stringify(payment));
		
		$.ajax({
			url:(isEdit?editPaymentUrl :registerPaymentUrl),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					if(isEdit)
						$.toast('modification success！');
					else $.toast('payment success！');
				}else{
					if(isEdit)
						$.toast('modification fail！'+data.errMsg);
					else $.toast('payment fail'+data.errMsg);
				}
			}
		});
	});

})
