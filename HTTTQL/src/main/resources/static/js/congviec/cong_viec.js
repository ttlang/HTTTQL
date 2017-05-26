$(document).ready(function() {
	$("#thoi_gian_bat_dau").datepicker();
	$("#thoi_gian_ket_thuc").datepicker();
	$("#thoi_gian_bat_dau2").datepicker();
	$("#thoi_gian_ket_thuc2").datepicker();
	$("#thoi_gian_bat_dau3").datepicker();
	$("#thoi_gian_ket_thuc3").datepicker();
	$("#thoi_gian_bat_dau4").datepicker();
	$("#thoi_gian_ket_thuc4").datepicker();
	
	
	
	// nút bỏ qua
	
	$('.cancle').on(function (e) {
		alert('hi')
		  $(this)
		    .find("input,textarea,select")
		       .val('')
		       .end()
		    .find("input[type=checkbox], input[type=radio]")
		       .prop("checked", "")
		       .end();
		})
	
	
	
});




// sử dụng cho modal công việc thường
function chon_bo_mon2() {
	var ma_bo_mon = $("#bo_mon2").children("option").filter(":selected").val();
	form_data = {
		ma_bo_mon : ma_bo_mon
	}
	$
			.ajax({
				url : '/congviec/LayMonHoc',
				type : 'POST',
				dataType : 'json',
				data : form_data,
				success : function(result) {
					$("#mon_hoc_group2").attr('class', 'visi');
					var json = $.parseJSON(JSON.stringify(result));
					$('#mon_hoc2').empty();
					$('#giang_vien_phu_trach2').empty();

					$('#mon_hoc2')
							.append(
									"<option selected='selected' disabled='disabled'>Chọn môn học</option>");
					for (var i = 0; i < json.length; i++) {
						var maMon = json[i].maMon;
						var tenMon = json[i].tenMon;
						$('#mon_hoc2').append($('<option>', {
							value : maMon,
							text : tenMon
						}));
					}

				}

			});

}


// dùng cho tạo đề cương
function chon_bo_mon3() {
	var ma_bo_mon = $("#bo_mon3").children("option").filter(":selected").val();
	form_data = {
		ma_bo_mon : ma_bo_mon
	}
	$
			.ajax({
				url : '/congviec/LayMonHoc',
				type : 'POST',
				dataType : 'json',
				data : form_data,
				success : function(result) {
					$("#mon_hoc_group3").attr('class', 'visi');
					var json = $.parseJSON(JSON.stringify(result));
					$('#mon_hoc3').empty();
					$('#giang_vien_phu_trach3').empty();

					$('#mon_hoc3')
							.append(
									"<option selected='selected' disabled='disabled'>Chọn môn học</option>");
					for (var i = 0; i < json.length; i++) {
						var maMon = json[i].maMon;
						var tenMon = json[i].tenMon;
						$('#mon_hoc3').append($('<option>', {
							value : maMon,
							text : tenMon
						}));
					}

				}

			});

}
//dùng cho tạo câu hỏi
function chon_bo_mon4() {
	var ma_bo_mon = $("#bo_mon4").children("option").filter(":selected").val();
	form_data = {
		ma_bo_mon : ma_bo_mon
	}
	$
			.ajax({
				url : '/congviec/LayMonHoc',
				type : 'POST',
				dataType : 'json',
				data : form_data,
				success : function(result) {
					$("#mon_hoc_group4").attr('class', 'visi');
					var json = $.parseJSON(JSON.stringify(result));
					$('#mon_hoc4').empty();
					$('#giang_vien_phu_trach4').empty();

					$('#mon_hoc4')
							.append(
									"<option selected='selected' disabled='disabled'>Chọn môn học</option>");
					for (var i = 0; i < json.length; i++) {
						var maMon = json[i].maMon;
						var tenMon = json[i].tenMon;
						$('#mon_hoc4').append($('<option>', {
							value : maMon,
							text : tenMon
						}));
					}

				}

			});

}


// sử dụng cho modal công việc thường
function chon_mon_hoc2() {
	var ma_mon_hoc = $("#mon_hoc2").children("option").filter(":selected").val();
	form_data = {
		ma_mon_hoc : ma_mon_hoc
	};
	$
			.ajax({
				url : '/congviec/GiangVienPhuTrach',
				type : 'POST',
				dataType : 'json',
				data : form_data,
				success : function(result) {
					var json = $.parseJSON(JSON.stringify(result));
					console.log(json);
					$("#giang_vien_group2").attr('class', 'visi');
					$('#giang_vien_phu_trach2').empty();
					if (json.length == 0) {
						$('#giang_vien_phu_trach2')
								.append(
										"<option disabled='disabled'>Không có giảng viên nào</option>");
						$("#phan_cong_group2").attr('class', 'hidden');
					} else {
						for (var i = 0; i < json.length; i++) {
							var maGV = json[i].magv;
							var tenGV = json[i].tengv;
							var hoGV = json[i].hogv;
							$('#giang_vien_phu_trach2').append($('<option>', {
								value : maGV,
								text : hoGV + ' ' + tenGV
							}));

						}
						$("#phan_cong_group2").attr('class', 'visi');
					}
				}

			});
}

// dùng cho tạo đề cương
function chon_mon_hoc3() {
	var ma_mon_hoc = $("#mon_hoc3").children("option").filter(":selected").val();
	form_data = {
		ma_mon_hoc : ma_mon_hoc
	};
	$
			.ajax({
				url : '/congviec/GiangVienPhuTrach',
				type : 'POST',
				dataType : 'json',
				data : form_data,
				success : function(result) {
					var json = $.parseJSON(JSON.stringify(result));
					console.log(json);
					$("#giang_vien_group3").attr('class', 'visi');
					$('#giang_vien_phu_trach3').empty();
					if (json.length == 0) {
						$('#giang_vien_phu_trach3')
								.append(
										"<option disabled='disabled'>Không có giảng viên nào</option>");
						$("#phan_cong_group3").attr('class', 'hidden');
					} else {
						for (var i = 0; i < json.length; i++) {
							var maGV = json[i].magv;
							var tenGV = json[i].tengv;
							var hoGV = json[i].hogv;
							$('#giang_vien_phu_trach3').append($('<option>', {
								value : maGV,
								text : hoGV + ' ' + tenGV
							}));

						}
						$("#phan_cong_group3").attr('class', 'visi');
						$("#tao_cau_truc_de_group3").attr('class', 'visi');
					}
				}

			});
}
// dùng cho tạo câu hỏi
function chon_mon_hoc4() {
	var ma_mon_hoc = $("#mon_hoc4").children("option").filter(":selected").val();
	form_data = {
		ma_mon_hoc : ma_mon_hoc
	};
	$
			.ajax({
				url : '/congviec/GiangVienPhuTrach',
				type : 'POST',
				dataType : 'json',
				data : form_data,
				success : function(result) {
					var json = $.parseJSON(JSON.stringify(result));
					console.log(json);
					$("#giang_vien_group4").attr('class', 'visi');
					$('#giang_vien_phu_trach4').empty();
					if (json.length == 0) {
						$('#giang_vien_phu_trach4')
								.append(
										"<option disabled='disabled'>Không có giảng viên nào</option>");
						$("#phan_cong_group4").attr('class', 'hidden');
					} else {
						for (var i = 0; i < json.length; i++) {
							var maGV = json[i].magv;
							var tenGV = json[i].tengv;
							var hoGV = json[i].hogv;
							$('#giang_vien_phu_trach4').append($('<option>', {
								value : maGV,
								text : hoGV + ' ' + tenGV
							}));

						}
						$("#phan_cong_group4").attr('class', 'visi');
						$("#tao_cau_hoi_group").attr('class', 'visi');
					}
				}

			});
	
	// lấy ra chương của môn học
	var form_data2 ={ma_mon_hoc:ma_mon_hoc}
	$.ajax({
		url : '/congviec/layChuongCuaMon',
		type : 'POST',
		dataType : 'json',
		data : form_data2,
		success : function(result) {
			console.log(result);
			var json = $.parseJSON(JSON.stringify(result));
			if(json.length == 0){
				$('#chuong_mon_hoc4').append($('<option>', {
					text : 'môn học chưa tạo cấu trúc chương'
				}));
			}else{
				for (var i = 0; i < json.length; i++) {
					$('#chuong_mon_hoc4').append($('<option>', {
						value : json[i].machuong,
						text : json[i].tieude
					}));
					
					
				}
				
			}
			
			}
		});
	
}

// công việc thường
function cong_viec_thuong(){
	var ma_bo_mon = $("#bo_mon2").children("option").filter(":selected").val();
	var ma_mon_hoc = $("#mon_hoc2").children("option").filter(":selected").val();
	var ds_giang_vien = [];
	$('#giang_vien_phu_trach2 :selected').each(function(i, selected) {
		ds_giang_vien[i] = selected.value;
	})
	var thoi_gian_bat_dau =$("#thoi_gian_bat_dau2").val();
	var thoi_gian_ket_thuc =$("#thoi_gian_ket_thuc2").val();
	var noi_dung_cong_viec =$("#noi_dung_cong_viec2").val();
	  var form_data ={
		ma_bo_mon : ma_bo_mon,
		ma_mon_hoc:ma_mon_hoc,
		ds_giang_vien:ds_giang_vien,
		thoi_gian_bat_dau:thoi_gian_bat_dau,
		thoi_gian_ket_thuc:thoi_gian_ket_thuc,
		noi_dung_cong_viec:noi_dung_cong_viec		
	};
	$.ajax({
			url : '/congviec/congviecthuong',
			type : 'POST',
			data :  form_data,
			success : function(result) {
					   $('#cong_viec_thuong').modal('toggle');
					   swal(result);
					   
					   
			}
			});
	
	
	return false;
}


// tạo đề cương
function tao_de_cuong(){
	
	var ma_bo_mon = $("#bo_mon3").children("option").filter(":selected").val();
	var ma_mon_hoc = $("#mon_hoc3").children("option").filter(":selected").val();
	var ds_giang_vien = [];
	$('#giang_vien_phu_trach3 :selected').each(function(i, selected) {
		ds_giang_vien[i] = selected.value;
	})
	var thoi_gian_bat_dau =$("#thoi_gian_bat_dau3").val();
	var thoi_gian_ket_thuc =$("#thoi_gian_ket_thuc3").val();
	var noi_dung_cong_viec =$("#noi_dung_cong_viec3").val();
	var so_luong_de_toi_da =$("#so_luong_de_toi_da3").val();
				 	form_data = {
						ma_bo_mon : ma_bo_mon,
						ma_mon_hoc:ma_mon_hoc,
						ds_giang_vien:ds_giang_vien,
						thoi_gian_bat_dau:thoi_gian_bat_dau,
						thoi_gian_ket_thuc:thoi_gian_ket_thuc,
						so_luong_de_toi_da:so_luong_de_toi_da,
						noi_dung_cong_viec:noi_dung_cong_viec
						
						
					};
				 	$.ajax({
						url : '/congviec/taodecuong',
						type : 'POST',
						data :  form_data,
						success : function(result) {
								   $('#tao_de_cuong').modal('toggle');
								   swal(result);
								   
								   
						}
						});	 	
				 	
				 	
	return false;
	
	
	
}
// xử lý cho chức năng tạo câu hỏi
function tao_cau_hoi(){
	var ma_bo_mon = $("#bo_mon4").children("option").filter(":selected").val();
	var ma_mon_hoc = $("#mon_hoc4").children("option").filter(":selected").val();
	var ds_giang_vien = [];
	$('#giang_vien_phu_trach4 :selected').each(function(i, selected) {
		ds_giang_vien[i] = selected.value;
	})
	var thoi_gian_bat_dau =$("#thoi_gian_bat_dau4").val();
	var thoi_gian_ket_thuc =$("#thoi_gian_ket_thuc4").val();
	var noi_dung_cong_viec =$("#noi_dung_cong_viec4").val();
	
	var so_luong_cau_hoi=$("#so_luong_cau_hoi4").val();
	var chuong_cua_mon=[];
	$('#chuong_mon_hoc4 :selected').each(function(i, selected) {
		chuong_cua_mon[i] = selected.value;
	})
	
	
	var form_data = {
		ma_bo_mon : ma_bo_mon,
		ma_mon_hoc:ma_mon_hoc,
		ds_giang_vien:ds_giang_vien,
		thoi_gian_bat_dau:thoi_gian_bat_dau,
		thoi_gian_ket_thuc:thoi_gian_ket_thuc,
		so_luong_cau_hoi:so_luong_cau_hoi,
		noi_dung_cong_viec:noi_dung_cong_viec,
		chuong_cua_mon:chuong_cua_mon
	};
	
//	alert('ma_bo_mon'+ma_bo_mon);
//	alert('ma_mon'+ma_mon_hoc);
//	alert(ds_giang_vien);
//	alert(thoi_gian_bat_dau);
//	alert(thoi_gian_ket_thuc);
//	alert('so_luong_cau_hoi'+so_luong_cau_hoi);
//	alert('noi_dung_cong_viec'+noi_dung_cong_viec);
//	alert('chuong cua mon'+chuong_cua_mon);
	$.ajax({
		url : '/congviec/taocauhoi',
		type : 'POST',
		data :  form_data,
		success : function(result) {
				   $('#tao_cau_hoi').modal('toggle');
				   swal(result);
				   
		}
		});	 	
	
	
	
	
	return false;
	
	
}

