/**
 * 
 */
/**
 * ;  *   
 */

$(function() {
	getlist();
	function getlist(e) {
		$.ajax({
			url : "/wds/vehicleadmin/getvehiclelist",
			type : "get",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					// 渲染列表
					handleList(data.vehicleList);
					// 渲染用户名
					handleCustomer(data.customer);
				}
			}
		});
	}
	function handleModifyCustomer(data) {
		document.getElementById("modify-customer").href = "/wds/customeradmin/customeroperation?customerId="
				+ data.customerId;
	}
	function handleVehicleOperation(data) {
		document.getElementById("vehicle-operation").href = "/wds/vehicleadmin/vehiclelist?customerId="
				+ data.customerId;
	}
	function handleCustomer(data) {
		$('#customer-name').text(
				data.customerFirstName + " " + data.customerLastName + ",UID:"
						+ data.customerId);
	}
	function handleList(data) {
		var html = '';
		data.map(function(item, index) {
			html += '<div class="row row-policy"><div class="col-10">'
					+ item.vehicleId + '</div><div class="col-30">'
					+ item.vehicleVin + '</div><div class="col-20">'
					+ item.vehicleMmy + '</div><div class="col-20">'
					+ item.vehicleStatus  
					+ '</div><div class="col-20">'
					+ goVehicle(item.vehicleId) 
					+ '</div></div>';
		});
		$('.vehicle-wrap').html(html);
	}
	
	function goVehicle(id) {
		return '<a href="/wds/vehicleadmin/vehicleoperation?vehicleId=' + id
				+ '">Modify</a>';
	}
})
