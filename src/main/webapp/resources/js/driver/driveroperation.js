/**
 * 
 */
$(function(){
	

	var customerId = getQueryString('customerId');
	var driverId = getQueryString('driverId');

	var isEdit = driverId ? true : false;
	// 注册driver的URL
	var registerDriverUrl = '/wds/driveradmin/registerdriver';
	// 编辑driver前需要获取店铺信息，这里为获取当前policy信息的URL
	var driverInfoUrl = "/wds/driveradmin/getdriverbyid?driverId=" + driverId;
	// 编辑policy信息的URL
	var editDriverUrl = '/wds/driveradmin/modifydriver';
	
	if(isEdit){
		getDriverInfo(driverId);
	}
	 
	function getDriverInfo(driverId){
		 $.getJSON(driverInfoUrl,function(data){
			 if(data.success){
				 var driver = data.driver;
				 $('#driver-license-id').val(driver.driverLicenseId);
				 $('#driver-last-name').val(driver.driverLastName);
				 $('#driver-first-name').val(driver.driverFirstName);
				 $('#driver-birth-date').val(driver.driverBirthDate);
			 }
		 });
	 }
	
	 $('#submit').click(function(){
		var driver ={};
		if(isEdit){
			driver.driverId=driverId;
		}
		
		driver.driverLicenseId = $('#driver-license-id').val();
		driver.driverLastName = $('#driver-last-name').val();
		driver.driverFirstName = $('#driver-first-name').val();
		driver.driverBirthDate= $('#driver-birth-date').val();
		
		var formData = new FormData();
		formData.append('driverStr',JSON.stringify(driver));
		formData.append('driverId',driverId);
		
		$.ajax({
			url:(isEdit?editDriverUrl :registerDriverUrl),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					if(isEdit)
						$.toast('modification success！');
					else $.toast('registration success！');
				}else{
					if(isEdit)
						$.toast('modification fail！'+data.errMsg);
					else $.toast('registration fail'+data.errMsg);
				}
			}
		});
	});

	 
})
