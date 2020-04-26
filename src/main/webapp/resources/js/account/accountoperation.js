/**
 * 
 */


$(function(){
//	var initUrl = '/wds/accountadmin/getaccountinitinfo';
	var registerAccountUrl = '/wds/accountadmin/registeraccount';
	var policyListUrl = '/wds/policyadmin/policylist';
	var loginAccountUrl = '/wds/accountadmin/loginaccount';
	var initLogInUrl = '/wds/accountadmin/initlogin';
	initlogin();
	function initlogin(e){
		var formData = new FormData();
		$.ajax({
			url:initLogInUrl,
			type:"POST",
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			dataType:"json",
			success:function(data){
				if(data.success){
					window.location.href=policyListUrl;
				}
			}
		});
	}
	
	$('#register').click(function(){
		var account = {};
		account.username=$('#username').val();
		account.password=$('#password').val();
		var formData = new FormData();
		formData.append('accountStr',JSON.stringify(account));
		
		$.ajax({
			url:(registerAccountUrl),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					$.toast("registration success");
				}
				else{
					$.toast("registration fail" + data.errMsg);
				}
			}
		})
		
	})
	
	$('#login').click(function(){
		var account = {};
		account.username=$('#username').val();
		account.password=$('#password').val();
		var formData = new FormData();
		formData.append('accountStr',JSON.stringify(account));
		login();
		function login(e){
			$.ajax({
				url:loginAccountUrl,
				type:"POST",
				data:formData,
				contentType:false,
				processData:false,
				cache:false,
				dataType:"json",
				success:function(data){
					if(data.success){
						window.location.href=policyListUrl;
					}
					else
					{
						$.toast("Fail to log in:" + data.errMsg);
					}
				}
			});
		}
//		$.ajax({
//			url:(loginAccountUrl),
//			type:'GET',
//			data:formData,
//			contentType:false,
//			processData:false,
//			cache:false,
//			success:function(data){
//				if(data.success){
//					$.toast("log in success");
//				}
//				else{
//					$.toast("log in fail" + data.errMsg);
//				}
//			}
//		})
		
	})
})