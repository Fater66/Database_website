/**
 * 
 */

$(function(){
	/*
	 * search
	 */
	$('#search').click(
	function()
			{
				var searchObj = document.getElementById("object-search");
				if(searchObj.value == "Policy")
					searchPolicyByCondition();
				else if(searchObj.value == "Home")
					searchHomeByCondition();
				else if(searchObj.value == "Vehicle")
					searchVehicleByCondition();
				else if(searchObj.value == "Driver")
					searchDriverByCondition();
				else if(searchObj.value == "Customer")
					searchCustomerByCondition();
			});
	
	function searchCustomerByCondition()
	{
		var customerCondition = {};
		customerCondition.customerGender = $('#customer-gender-search').val();
		customerCondition.customerLastName = $('#customer-last-name-search').val();
		customerCondition.customerFirstName = $('#customer-first-name-search').val();
		customerCondition.customerMaritalStatus = $('#customer-marital-status-search').val();
		var formData = new FormData();

		formData.append('customerConditionStr',JSON.stringify(customerCondition));
		
		$.ajax({
			url:("/wds/customeradmin/searchcustomerlist"),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					handlecustomerTitle();
					handlecustomerList(data.customerList);
				}else{
					$.toast('search fail'+data.errMsg);
				}
			}
		});
	}
	
	function searchHomeByCondition()
	{
		var homeCondition = {};
		homeCondition.homeType = $('#home-type-search').val();
		var formData = new FormData();
		formData.append('minValueStr',$('#min-value-home-search').val());
		formData.append('maxValueStr',$('#max-value-home-search').val());
		formData.append('minDateStr',$('#min-date-home-search').val());
		formData.append('maxDateStr',$('#max-date-home-search').val());
		formData.append('minAreaStr',$('#min-area-home-search').val());
		formData.append('maxAreaStr',$('#max-area-home-search').val());
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
					handlehomeTitle();
					handlehomeList(data.homeList);
				}else{
					$.toast('search fail'+data.errMsg);
				}
			}
		});
	}
	
	function searchPolicyByCondition()
	{
		var policyCondition = {};
		policyCondition.type = ($('#type-search').val() == 'Auto Insurance')?'A':'H';
		policyCondition.startDate = $('#start-date-search').val();
		policyCondition.endDate = $('#end-date-search').val();
		policyCondition.premiumAmount = $('#premium-amount-search').val();
		var formData = new FormData();
		formData.append('policyConditionStr',JSON.stringify(policyCondition));
		
		$.ajax({
			url:("/wds/policyadmin/searchpolicylist"),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					handlepolicyList(data.policyList);
				}else{
					$.toast('search fail'+data.errMsg);
				}
			}
		});
	}
	
	function searchVehicleByCondition()
	{
		var vehicleCondition = {};
		if($('#vehicle-vin-search').val()!="") vehicleCondition.vehicleVin = $('#vehicle-vin-search').val();
		if($('#vehicle-mmy-search').val()!="") vehicleCondition.vehicleMmy = $('#vehicle-mmy-search').val();
		vehicleCondition.vehicleStatus = $('#vehicle-status-search').val();
		var formData = new FormData();
		formData.append('vehicleConditionStr',JSON.stringify(vehicleCondition));
		$.ajax({
			url:("/wds/vehicleadmin/searchvehiclelist"),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					handlevehicleTitle();
					handlevehicleList(data.vehicleList);
				}else{
					$.toast('search fail'+data.errMsg);
				}
			}
		});
	}
	
	function searchDriverByCondition()
	{
		var driverCondition = {};
		if($('#driver-license-id-search').val()!="") driverCondition.driverLicenseId = $('#driver-license-id-search').val();
		if($('#driver-last-name-search').val()!="") driverCondition.driverLastName = $('#driver-last-name-search').val();
		if($('#driver-first-name-search').val()!="") driverCondition.driverFirstName= $('#driver-first-name-search').val();
		var formData = new FormData();
		formData.append('minDateStr',$('#min-date-driver-search').val());
		formData.append('maxDateStr',$('#max-date-driver-search').val());
		formData.append('driverConditionStr',JSON.stringify(driverCondition));
		$.ajax({
			url:("/wds/driveradmin/searchdriverlist"),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					handledriverTitle();
					handledriverList(data.driverList);
				}else{
					$.toast('search fail'+data.errMsg);
				}
			}
		});
	}
	/*
	 * customer view part
	 */
	$('#view-customer').click(function(){getcustomerList()});
	
	function getcustomerList(e){
		$.ajax({
			url:"/wds/customeradmin/listcustomer",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handlecustomerTitle();
					handlecustomerList(data.customerList);
				}
			}
		});
	}
	
	function handlecustomerTitle()
	{
		var html = '';
		html+= '<div class="row row-policy"><div class="col-15">'
			+'Last Name</div><div class="col-15">'
			+'First Name</div><div class="col-15">'
			+'Gender</div><div class="col-20">'
			+'Martital Status</div><div class="col-15">'
			+'Modify</div>'
			+'<div class="col-15">' + 'Delete'
			+'</div>'
		$('.title-view').html(html);
	}
	function handlecustomerList(data)
	{
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-15">'
				+ item.customerLastName + '</div><div class="col-15">'
				+ item.customerFirstName
				+ '</div><div class="col-15">'
				+ item.customerGender
				+ '</div><div class="col-20">'
				+ item.customerMaritalStatus
				+ '</div><div class="col-15">'
				+ '<a href="/wds/customeradmin/customeroperation?customerId='+item.customerId+'">Modify</a>'
				+ '</div><div class="col-15">'
				+ '<a class="button button-danger" onClick = "deleteCustomer('+item.customerId+')">Delete</a>'
				+ '</div></div>';
		});
		$('.view-wrap').html(html);
	}
	
	/*
	 * policy view part
	 */
	$('#view-policy').click(function(){getpolicyList()});
	
	function getpolicyList(e){
		$.ajax({
			url:"/wds/policyadmin/listpolicy",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handlepolicyTitle();
					handlepolicyList(data.policyList);
				}
			}
		});
	}
	
	function handlepolicyTitle()
	{
		var html = '';
		html+= '<div class="row row-policy"><div class="col-20">Policy Type</div>'
			+'<div class="col-20">Start Date</div>'
			+'<div class="col-20">End Date</div>'
			+'<div class="col-20">Information</div>'
			+'<div class="col-10">Status</div></div>';
		$('.title-view').html(html);
	}
	function handlepolicyList(data)
	{
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-20">'
				+ item.type + '</div><div class="col-20">'
				+ item.startDate
				+ '</div><div class="col-20">'
				+ item.endDate
				+ '</div><div class="col-20">'
				+ '<a href="/wds/policyadmin/policyoperation?policyId='+item.policyId+'">Modify</a>'
				+ '</div><div class="col-20">'
				+ '<a class="button button-danger" onClick = "deletePolicy('+item.policyId+')">Delete</a>'
				+ '</div></div>';
		});
		$('.view-wrap').html(html);
	}
	
	/*
	 * home 
	 */
	
	$('#view-home').click(function(){gethomeList()});
	
	function gethomeList(e){
		$.ajax({
			url:"/wds/homeadmin/listhome",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handlehomeTitle();
					handlehomeList(data.homeList);
				}
			}
		});
	}
	
	function handlehomeTitle()
	{
		var html = '';
		html+= '<div class="row row-policy"><div class="col-20">Purchase Date</div>'
			+'<div class="col-20">Purchase Value</div>'
			+'<div class="col-20">Home Area</div>'
			+'<div class="col-20">Modify</div>'
			+'<div class="col-10">Delete</div></div>';
		$('.title-view').html(html);
	}
	
	function handlehomeList(data)
	{
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-20">'
				+ item.homePurchaseDate + '</div><div class="col-20">'
				+ item.homePurchaseValue
				+ '</div><div class="col-20">'
				+ item.homeArea
				+ '</div><div class="col-20">'
				+ '<a href="/wds/homeadmin/homeoperation?homeId='+item.homeId+'">Modify</a>'
				+ '</div><div class="col-20">'
				+ '<a class="button button-danger" onClick = "deleteHome('+item.homeId+')">Delete</a>'
				+ '</div></div>';
		});
		$('.view-wrap').html(html);
	}
	
	/*
	 * vehicle 
	 */
	
	$('#view-vehicle').click(function(){getvehicleList()});
	
	function getvehicleList(e){
		$.ajax({
			url:"/wds/vehicleadmin/listvehicle",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handlevehicleTitle();
					handlevehicleList(data.vehicleList);
				}
			}
		});
	}
	
	function handlevehicleTitle()
	{
		var html = '';
		html+= '<div class="row row-policy"><div class="col-10">Id</div>'
			+'<div class="col-38">Vehicle Vin Number</div>'
			+'<div class="col-20">Mmy</div>'
			+'<div class="col-10">Status</div>'
			+'<div class="col-15">Modify</div>'
			+'<div class="col-15">Delete</div></div>';
		$('.title-view').html(html);
	}
	
	function handlevehicleList(data)
	{
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-10">'
				+ item.vehicleId + '</div><div class="col-30">'
				+ item.vehicleVin + '</div><div class="col-20">'
				+ item.vehicleMmy + '</div><div class="col-10">'
				+ item.vehicleStatus  
				+ '</div><div class="col-15">'
				+ '<a href="/wds/vehicleadmin/vehicleoperation?vehicleId='+item.vehicleId+'">Modify</a>'
				+ '</div><div class="col-15">'
				+ '<a class="button button-danger" onClick = "deleteVehicle('+item.vehicleId+')">Delete</a>'
				+ '</div></div>';
		});
		$('.view-wrap').html(html);
	}
	
	/*
	 * driver 
	 */
	
	$('#view-driver').click(function(){getdriverList()});
	
	function getdriverList(e){
		$.ajax({
			url:"/wds/driveradmin/listdriver",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					//渲染列表
					handledriverTitle();
					handledriverList(data.driverList);
				}
			}
		});
	}
	
	function handledriverTitle()
	{
		var html = '';
		html+= '<div class="row row-policy"><div class="col-20">License Id</div>'
			+'<div class="col-20">Last Name</div>'
			+'<div class="col-20">First Name</div>'
			+'<div class="col-20">Birth Date</div>'
			+'<div class="col-10">Modify</div>'
			+'<div class="col-10">Delete</div></div>';
		$('.title-view').html(html);
	}
	
	function handledriverList(data)
	{
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-policy"><div class="col-20">'
				+ item.driverLicenseId + '</div><div class="col-20">'
				+ item.driverLastName
				+ '</div><div class="col-20">'
				+ item.driverFirstName
				+ '</div><div class="col-20">'
				+ item.driverBirthDate
				+'</div><div class="col-10">'
				+ '<a href="/wds/driveradmin/driveroperation?driverId='+item.driverId+'">Modify</a>'
				+ '</div><div class="col-10">'
				+ '<a class="button button-danger" onClick = "deleteDriver('+item.driverId+')">Delete</a>'
				+ '</div></div>';
		});
		$('.view-wrap').html(html);
	}
})