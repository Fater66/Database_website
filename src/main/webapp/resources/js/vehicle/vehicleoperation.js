/**
 * 
 */
$(function(){
	
//	// 从URL里获取shopId参数的值
	var customerId = getQueryString('customerId');
	var vehicleId = getQueryString('vehicleId');
	// 由于店铺注册和编辑使用的是同一个页面，
	// 该标识符用来标明本次是添加还是编辑操作
	var isEdit = vehicleId ? true : false;
	// 注册vehicle的URL
	var registerVehicleUrl = '/wds/vehicleadmin/registervehicle';
	// 编辑vehicle前需要获取店铺信息，这里为获取当前policy信息的URL
	var vehicleInfoUrl = "/wds/vehicleadmin/getvehiclebyid?vehicleId=" + vehicleId;
	// 编辑vehicle信息的URL
	var editVehicleUrl = '/wds/vehicleadmin/modifyvehicle';
	// 获取driver信息
	var viewDriverUrl='/wds/driveradmin/getdriverlist';
	var ownDriverUrl='/wds/driveradmin/getdriverlistbyvehicleid?vehicleId='+vehicleId;
	editDriverList();
	if(isEdit){
		getVehicleInfo(vehicleId);
		viewDriverList();
	}
	
	
	function viewDriverList(e){
		 $.ajax({
				url:ownDriverUrl,
				type:"get",
				dataType:"json",
				success:function(data){
					if(data.success){
						ownDriverList(data.driverList);
					}
				}
			});
		}
	 function ownDriverList(data)
	 {
		 var html = '<li><div class="item-content"><div class="item-inner"><div class="item-title label">Driver List</div></div></div>';
			data.map(function(item,index){
					html+='<label class="label-checkbox item-content"><input type="checkbox" checked=true name="driverList" value="'+item.driverId
					+'"><div class="item-media"><i class="icon icon-form-checkbox"></i></div><div class="item-inner"><div class="item-title-row"><div class="item-title">'
					+'Driver License id:'+item.driverLicenseId
					+'</div><div class="item-after">'
					+'DriverId:'+item.driverId
					+'</div></div><div class="item-subtitle">'
					+'DriverName:'+item.driverFirstName+item.driverLastName
					+'</div><div class="item-text">'
					+'DriverBirthdate:'+item.driverBirthDate
					+'</div></div></label>';
				});
				html +='</li>';
				$('.driver-wrap').html(html);
	 }
	 
	function getVehicleInfo(vehicleId){
		 $.getJSON(vehicleInfoUrl,function(data){
			 if(data.success){
				 var vehicle = data.vehicle;
				 $('#vehicle-vin').val(vehicle.vehicleVin);
				 $('#vehicle-mmy').val(vehicle.vehicleMmy);
				 $('#vehicle-status').val(vehicle.vehicleStatus);
			 }
		 });
	 }
	
	 $('#submit').click(function(){
		var vehicle ={};
		if(isEdit){
			vehicle.vehicleId=vehicleId;
		}
		
		vehicle.vehicleVin = $('#vehicle-vin').val();
		vehicle.vehicleMmy = $('#vehicle-mmy').val();
		vehicle.vehicleStatus = $('#vehicle-status').val();

		
		// 获取多选的driver
		driverBlock = document.getElementsByName("driverList");
		driverVehicleList =[];
		for(k in driverBlock)
		{
			 if(driverBlock[k].checked)
				 driverVehicleList.push(driverBlock[k].value);
		}
		
		var formData = new FormData();
		formData.append('vehicleStr',JSON.stringify(vehicle));
		formData.append('vehicleId',vehicleId);
		formData.append('driverVehicleListStr',driverVehicleList);
		
		$.ajax({
			url:(isEdit?editVehicleUrl :registerVehicleUrl),
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

	 function colorDriverList(data)
	 {
		 var html = '<li><div class="item-content"><div class="item-inner"><div class="item-title label">Driver List</div></div></div>';
		data.map(function(item,index){
				html+='<label class="label-checkbox item-content"><input type="checkbox" name="driverList" value="'+item.driverId
				+'"><div class="item-media"><i class="icon icon-form-checkbox"></i></div><div class="item-inner"><div class="item-title-row"><div class="item-title">'
				+'Driver License id:'+item.driverLicenseId
				+'</div><div class="item-after">'
				+'DriverId:'+item.driverId
				+'</div></div><div class="item-subtitle">'
				+'DriverName:'+item.driverFirstName+item.driverLastName
				+'</div><div class="item-text">'
				+'DriverBirthdate:'+item.driverBirthDate
				+'</div></div></label>';
			});
			html +='</li>';
			$('.driver-wrap').html(html);
	 }
	 
	 function editDriverList(e){
		 $.ajax({
				url:"/wds/driveradmin/getdriverlist",
				type:"get",
				dataType:"json",
				success:function(data){
					if(data.success){
						colorDriverList(data.driverList);
					}
				}
			});
		}
	 
})
