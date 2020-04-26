/**
 * 
 */
$(function(){
	
//	// 从URL里获取shopId参数的值
	var customerId = getQueryString('customerId');
	var homeId = getQueryString('homeId');
	// 由于店铺注册和编辑使用的是同一个页面，
	// 该标识符用来标明本次是添加还是编辑操作
	var isEdit = homeId ? true : false;
	// 注册home的URL
	var registerHomeUrl = '/wds/homeadmin/registerhome';
	// 编辑home前需要获取店铺信息，这里为获取当前policy信息的URL
	var homeInfoUrl = "/wds/homeadmin/gethomebyid?homeId=" + homeId;
	// 编辑policy信息的URL
	var editHomeUrl = '/wds/homeadmin/modifyhome';
	
	if(isEdit){
		getHomeInfo(homeId);
	}
	 
	function getHomeInfo(homeId){
		 $.getJSON(homeInfoUrl,function(data){
			 if(data.success){
				 var home = data.home;
				 $('#home-purchase-date').val(home.homePurchaseDate);
				 $('#home-purchase-value').val(home.homePurchaseValue);
				 $('#home-area').val(home.homeArea);
				 $('#home-type').val(home.homeType);
				 $('#auto-fire-notification').val(home.autoFireNotification);
				 $('#home-security-system').val(home.homeSecuritySystem);
				 $('#swimming-pool').val(home.swimmingPool);
				 $('#basement').val(home.basement);
			 }
		 });
	 }
	
	 $('#submit').click(function(){
		var home ={};
		if(isEdit){
			home.homeId=homeId;
		}
		
		home.homePurchaseDate = $('#home-purchase-date').val();
		home.homePurchaseValue = $('#home-purchase-value').val();
		home.homeArea = $('#home-area').val();
		home.homeType = $('#home-type').val();
		home.autoFireNotification = $('#auto-fire-notification').val();
		home.homeSecuritySystem = $('#home-security-system').val();
		home.swimmingPool = $('#swimming-pool').val();
		home.basement = $('#basement').val();
		
		var formData = new FormData();
		formData.append('homeStr',JSON.stringify(home));
		formData.append('homeId',homeId);
		
		$.ajax({
			url:(isEdit?editHomeUrl :registerHomeUrl),
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
