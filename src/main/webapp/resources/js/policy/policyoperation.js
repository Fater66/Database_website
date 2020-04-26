/**
 * 
 */
$(function(){
	
// // 从URL里获取shopId参数的值
	var customerId = getQueryString('customerId');
	var policyId = getQueryString('policyId');
	// 由于店铺注册和编辑使用的是同一个页面，
	// 该标识符用来标明本次是添加还是编辑操作
	var isEdit = policyId ? true : false;
	// 用于policy注册时候的初始化的URL
	var initUrl = '/wds/policyadmin/getpolicyinitinfo';
	// 注册policy的URL
	var registerPolicyUrl = '/wds/policyadmin/registerpolicy';
	// 编辑policy前需要获取店铺信息，这里为获取当前policy信息的URL
	var policyInfoUrl = "/wds/policyadmin/getpolicybyid?policyId=" + policyId;
	
	// 编辑policy信息的URL
	var editPolicyUrl = '/wds/policyadmin/modifypolicy';
	// 获取home和vehicle的
	var viewHomeUrl='/wds/homeadmin/gethomelistbypolicyid?policyId='+policyId;
	var viewVehicleUrl='/wds/vehicleadmin/getvehiclelistbypolicyid?policyId='+policyId;
	
	if(!isEdit){
// getPolicyInitInfo();
		editHomeList();
		editVehicleList();
	}else{
		getPolicyInfo(policyId);
	}
	initColor();
	
	function initColor(){
		 var homeListView = document.getElementById("home-list-view");
		 var vehicleListView = document.getElementById("vehicle-list-view");
		 var durationView = document.getElementById("duration");
		 var endDateView = document.getElementById("end-date-view");
		 if(!isEdit)
		 {
			 homeListView.style.display="none";
			 vehicleListView.style.display="none";
			 endDateView.style.display="none";
		 }
		 else
		 {
			 durationView.style.display ="none";
			 endDateView.style.display="block";
		 }
	}
	 function getPolicyInfo(policyId){
		 $.getJSON(policyInfoUrl,function(data){
			 if(data.success){
				 var policy = data.policy;
				 $('#type').val(typeTransfer(policy.type));
				 $('#start-date').val(policy.startDate);
				 $('#end-date').val(policy.endDate);
				 $('#premium-amount').val(policy.premiumAmount);
				 $('#type').attr('disabled','disabled');
				 $('#start-date').attr('disabled','disabled');
				 $('#end-date').attr('disabled','disabled');
				 $('#premium-amount').attr('disabled','disabled');
				 if(policy.type=='H')
				 {
					 viewHomeList();
				 }
				 else
					viewVehicleList();
			 }
		 });
	 }
	 
	 function typeTransfer(type)
	 {
		 if(type == "A")
			 return "Auto Insurance";
		 else return "Home Insurance";
	 }
	 
	 $('#show').click(function()
	{
		 obj = document.getElementsByName("test");
		 check_val =[];
		 for(k in obj)
		 {
			 if(obj[k].checked)
				 check_val.push(obj[k].value);
		 }
		 alert(check_val);
	});
	 
	 function editHomeList(e){
		 $.ajax({
				url:"/wds/homeadmin/gethomelist",
				type:"get",
				dataType:"json",
				success:function(data){
					if(data.success){
						colorHomeList(data.homeList);
					}
				}
			});
		}
	 
	 
	 $('#register').click(function(){
		var policy ={};
		var home ={};
		var auto ={};
		if(isEdit){
			policy.policyId=policyId;
		}
		
		if(!isEdit) policy.status = 'C';
		policy.type = ($('#type').val() == 'Auto Insurance')?'A':'H';
		policy.startDate = $('#start-date').val();
		policy.endDate = $('#end-date').val();
		policy.premiumAmount = $('#premium-amount').val();
		
		// 获取多选的home或者vehicle，depends on policy type
		vehiclePolicyBlock = document.getElementsByName("vehiclePolicyList");
		vehiclePolicyList =[];
		homePolicyBlock = document.getElementsByName("homePolicyList");
		homePolicyList =[];
		if(policy.type=='H')
		{
			for(k in homePolicyBlock)
			{
				 if(homePolicyBlock[k].checked)
					 homePolicyList.push(homePolicyBlock[k].value);
			}
		}
		else
		{
			for(k in vehiclePolicyBlock)
			{
				 if(vehiclePolicyBlock[k].checked)
					 vehiclePolicyList.push(vehiclePolicyBlock[k].value);
			}
		}
		var formData = new FormData();
		formData.append('policyStr',JSON.stringify(policy));
		formData.append('homeStr',JSON.stringify(home));
		formData.append('customerId',customerId);
		formData.append('homePolicyListStr',homePolicyList);
		formData.append('vehiclePolicyListStr',vehiclePolicyList);
		formData.append('durationYearStr',$('#duration-year').val());
		formData.append('durationMonthStr',$('#duration-month').val());
		formData.append('installmentStr',installmentTransfer($('#installment').val()));
		
		$.ajax({
			url:(registerPolicyUrl),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					$.toast('registration success！');
				}else{
					$.toast('registration fail'+data.errMsg);
				}
			}
		});
	});

	 function installmentTransfer(installment)
	 {
		 if(installment == 'One-time')
			 return 0;
		 else if(installment == 'Monthly')
			 return 1;
		 else if(installment == 'Quarterly')
			 return 4;
		 else if(installment == 'Yearly')
			 return 12;
	 }
	 
	 function viewHomeList(e){
		 $.ajax({
				url:viewHomeUrl,
				type:"get",
				dataType:"json",
				success:function(data){
					if(data.success){
						ownHomeList(data.homeList);
					}
				}
			});
		}
	 function ownHomeList(data)
	 {
		 var html = '<li><div class="item-content"><div class="item-inner"><div class="item-title label">Customer Home List</div></div></div>';
		data.map(function(item,index){
				html+=
				'<label class="label-checkbox item-content"><input type="checkbox" disabled=true checked=true name="homePolicyList" value="'
				+item.homeId
				+'"><div class="item-media"><i class="icon icon-form-checkbox"></i></div><div class="item-inner"><div class="item-title-row"><div class="item-title">'
				+'Purchase Date:'+item.homePurchaseDate
				+'</div><div class="item-after">'
				+'HomeId:'+item.homeId
				+'</div></div><div class="item-subtitle">'
				+'HomeArea:'+item.homeArea
				+'</div><div class="item-text">'
				+'HomeType:'+item.homeType
				+'</div></div></label>';
			});
			html +='</li>';
			$('.home-wrap').html(html);
	 }
	 
	 function colorHomeList(data)
	 {
		 var html = '<li><div class="item-content"><div class="item-inner"><div class="item-title label">Customer Home List</div></div></div>';
		data.map(function(item,index){
				html+='<label class="label-checkbox item-content"><input type="checkbox" name="homePolicyList" value="'+item.homeId
				+'"><div class="item-media"><i class="icon icon-form-checkbox"></i></div><div class="item-inner"><div class="item-title-row"><div class="item-title">'
				+'Purchase Date:'+item.homePurchaseDate
				+'</div><div class="item-after">'
				+'HomeId:'+item.homeId
				+'</div></div><div class="item-subtitle">'
				+'HomeArea:'+item.homeArea
				+'</div><div class="item-text">'
				+'HomeType:'+item.homeType
				+'</div></div></label>';
			});
			html +='</li>';
			$('.home-wrap').html(html);
	 }
	 
	 function viewVehicleList(e){
		 $.ajax({
				url:viewVehicleUrl,
				type:"get",
				dataType:"json",
				success:function(data){
					if(data.success){
						ownVehicleList(data.vehicleList);
					}
				}
			});
		}
	 function ownVehicleList(data)
	 {
		 var html = '<li><div class="item-content"><div class="item-inner"><div class="item-title label">Customer Vehicle List</div></div></div>';
		data.map(function(item,index){
				html+=
				'<label class="label-checkbox item-content"><input type="checkbox" disabled=true checked=true name="vehiclePolicyList" value="'
				+item.vehicleId
				+'"><div class="item-media"><i class="icon icon-form-checkbox"></i></div><div class="item-inner"><div class="item-title-row"><div class="item-title">'
				+'Vehicle MMY:'+item.vehicleMmy
				+'</div><div class="item-after">'
				+'VehicleId:'+item.vehicleId
				+'</div></div><div class="item-subtitle">'
				+'VehicleVin:'+item.vehicleVin
				+'</div><div class="item-text">'
				+'VehicleStatus:'+item.vehicleStatus
				+'</div></div></label>';
			});
			html +='</li>';
			$('.vehicle-wrap').html(html);
	 }
	 
	 function colorVehicleList(data)
	 {
		 var html = '<li><div class="item-content"><div class="item-inner"><div class="item-title label">Customer Vehicle List</div></div></div>';
		data.map(function(item,index){
				html+='<label class="label-checkbox item-content"><input type="checkbox" name="vehiclePolicyList" value="'+item.vehicleId
				+'"><div class="item-media"><i class="icon icon-form-checkbox"></i></div><div class="item-inner"><div class="item-title-row"><div class="item-title">'
				+'Vehicle MMY:'+item.vehicleMmy
				+'</div><div class="item-after">'
				+'VehicleId:'+item.vehicleId
				+'</div></div><div class="item-subtitle">'
				+'VehicleVin:'+item.vehicleVin
				+'</div><div class="item-text">'
				+'VehicleStatus:'+item.vehicleStatus
				+'</div></div></label>';
			});
			html +='</li>';
			$('.vehicle-wrap').html(html);
	 }
	 
	 function editVehicleList(e){
		 $.ajax({
				url:"/wds/vehicleadmin/getvehiclelist",
				type:"get",
				dataType:"json",
				success:function(data){
					if(data.success){
						colorVehicleList(data.vehicleList);
					}
				}
			});
		}
})
