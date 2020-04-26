/**
 * 
 */

$(function(){
	var customerId = getQueryString('customerId');
	var customerInfoUrl = "/wds/customeradmin/getcustomerbyid?customerId=" + customerId;
	var modifyCustomerUrl="/wds/customeradmin/modifycustomer";
	var isEdit = customerId?true:false;
	
	getCustomerInfo(customerId);
	function getCustomerInfo(customerId){
		$.getJSON(customerInfoUrl,function(data){
			if(data.success){
				var customer = data.customer;
				$('#last-name').val(customer.customerLastName);
				$('#first-name').val(customer.customerFirstName);
				$('#street-address').val(customer.customerStreetAddress);
				$('#city').val(customer.customerCity);
				$('#state').val(customer.customerState);
				$('#zipcode').val(customer.customerZipcode);
				$('#gender').val(customer.customerGender);
				$('#marital-status').val(customer.customerMaritalStatus);
			}
		})
	}
	
	$('#submit').click(function(){
		var customer ={};
		if(isEdit){
			customer.customerId=customerId;
		}
		
		customer.customerLastName=$('#last-name').val();
		customer.customerFirstName=$('#first-name').val();
		customer.customerStreetAddress=$('#street-address').val();
		customer.customerCity=$('#city').val();
		customer.customerState=$('#state').val();
		customer.customerZipcode=$('#zipcode').val();
		customer.customerGender=$('#gender').val();
		customer.customerMaritalStatus =$('#marital-status').val();
		
		var formData = new FormData();
		formData.append('customerStr',JSON.stringify(customer));
		formData.append('customerId',customerId);
		$.ajax({
			url:(modifyCustomerUrl),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					$.toast('modify success！');
				}else{
					$.toast('modify fail'+data.errMsg);
				}
			}
		});
	});
})