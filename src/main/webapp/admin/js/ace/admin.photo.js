jQuery.adminOrder = {
		orderDataTable:null,
		toSave:false,
		initSearchDataTable : function() {
			if (this.orderDataTable == null) {
				this.orderDataTable = $('#dt_order_view').dataTable({
					"sDom" : "<'row-fluid'<'span6'l>r>t<'row-fluid'<'span6'i><'span6'p>>",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "每页显示 _MENU_ 条记录",
						"sZeroRecords" : "抱歉， 暂时没有记录",
						"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
						"sSearch" : "",
						"sInfoEmpty" : "没有数据",
						"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : "前一页",
							"sNext" : "后一页",
							"sLast" : "尾页"
						}
					},
					"bAutoWidth" : false,
					"iDisplayLength" : 10,
					"aLengthMenu" : [ 5, 10, 25, 50],
					"bServerSide" : true,
					"sServerMethod" : "POST",
					"bProcessing" : true,
					"bSort" : false,
					"sAjaxSource" : $.ace.getContextPath() + "/admin/photo/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var state="";
						var name = $("#state").val();
						if (!!name) {
							aoData.push({
								"name" : "name",
								"value" : name
							});
						}
						$.ajax({
							"dataType" : 'json',
							"type" : "POST",
							"url" : sSource,
							"data" : aoData,
							"success" : function(data){
								fnCallback(data.resultMap);
							}
						});
					},
					"aoColumns" : [{
						"mDataProp" : "url"
					}, {
						"mDataProp" : "category.name"
					}, {
						"mDataProp" : "state"
					}, {
						"mDataProp" : "createDate"
					}, {
						"mDataProp" : "uploader"
					}, {
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [0],
							'fnRender' : function(oObj, sVal) {
								return "<img style='height: 40px' src='"+$.ace.getContextPath()+"/upload/"+oObj.aData.url+"' class='img-rounded'>";
							}
						},
						{
							'aTargets' : [1],
							'fnRender' : function(oObj, sVal) {
								return "<span class='label label-info'>"+sVal+"</span>   ";
							}
						},
						{
							'aTargets' : [2],
							'fnRender' : function(oObj, sVal) {
								state=sVal;
								if(sVal=='待审核')
									return "<span class='label label-important'>待审核</span>";
								else if(sVal=='合格')
									return "<span class='label label-success'>合格</span>";
								else(sVal=='不合格')
								return "<span class='label label-info'>不合格</span>";
							}
						},
						
						{
							'aTargets' : [5],
							'fnRender' : function(oObj, sVal) {
								if(state=='待审核')
								return "<button class=\"btn2 btn-info\" onclick=\"$.adminOrder.approveOk('"+oObj.aData.id+"')\"><i class=\"icon-thumbs-up\"></i>合格</button>"+
								 " <button class=\"btn2 btn-danger\" onclick=\"$.adminOrder.approveNotOk('"+oObj.aData.id+"')\"><i class=\"icon-thumbs-down\"></i>不合格</button>";
								
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.orderDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.orderDataTable.fnDraw(oSettings);
			}

		},
		approveNotOk :function(id){
			bootbox.confirm( "是否确认审核不通过？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/photo/approvenotok?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminOrder.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		approveOk :function(id){
			bootbox.confirm( "是否确认审核通过？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/photo/approveok?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminOrder.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		showaddModal: function(id){
			$.adminOrder.toSave=true;
			$("#user_modal_header_label").text("新增分类");
			$("#order_modal").modal('show');
		},
		save : function (){
			if($.adminOrder.toSave){
				$.ajax({
	    			type : "post",
	    			url : $.ace.getContextPath() + "/admin/order/save",
	    			data:{
	    				"order.name":$("#name").val(),
	    				"order.remark":$("#remark").val()
	    			},
	    			dataType : "json",
	    			success : function(json) {
	    				if(json.resultMap.state=='success'){
	    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	    					$.adminOrder.initSearchDataTable();
	    					$("#order_modal").modal('hide');
	    				}else{
	    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	    				}
	    			}
	    		});
			}else{
				$.ajax({
	    			type : "post",
	    			url : $.ace.getContextPath() + "/admin/order/update",
	    			data:{
	    				"order.id":$("#orderId").val(),
	    				"order.name":$("#name").val(),
	    				"orderSub.remark":$("#remark").val()
	    			},
	    			dataType : "json",
	    			success : function(json) {
	    				if(json.resultMap.state=='success'){
	    					$("#user_edit_modal").modal('hide');
	    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	    					$.adminOrder.initSearchDataTable();
	    					$("#order_modal").modal('hide');
	    				}else{
	    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	    				}
	    			}
	    		});
			}
		},
		showEdit: function (id){
			$("#orderId").val(id);
			$.adminOrder.toSave=false;
			$.ajax({
    			type : "get",
    			url : $.ace.getContextPath() + "/admin/order/get?id="+id,
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#user_modal_header_label").text("修改分类");
    					$("#order_modal").modal('show');
    					$("#name").val(json.resultMap.orderSub.name);
    					$("#pid").val(json.resultMap.orderSub.order.id);
    					$("#remark").val(json.resultMap.orderSub.remark);
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		}
};