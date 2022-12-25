if (!johnny) {
	var johnny = {}
};
$.extend(true, johnny, {
	resetFilter : function(resetBtn) {
		resetBtn.click(function() {
			$(".filterdata").val("");
			johnny.updateGrid()
		});
	},
	getGridDataValue : function(dataGridDatas, key) {
		var result;
		$(dataGridDatas).each(function(index, value) {
			if (value) {
				if (value.name == key) {
					result = value.value;
					return false
				} else {
					return true;
				}
			}

		});
		return result;

	},

	getSortCol : function(dataGridDatas) {
		return this.getGridDataValue(dataGridDatas, 'iSortCol_0');
	},
	getSortDirect : function(dataGridDatas) {
		return this.getGridDataValue(dataGridDatas, 'sSortDir_0');
	},
	getRules : function(){
		var rules = "";
		$(".filterdata").each(
				function(index, value) {
					if ($(this).attr("type") ==='checkbox'){
						if ($( this ).is(":checked")){
							rules +=johnny.getRuleByInput(this);
						}
						
					}else{
					if ($(this).val()) {
						rules += johnny.getRuleByInput(this);
					}
					}
				});
		rules = rules.substring(0, rules.length - 1)
		return rules;
	},
	
	getRuleByInput : function(obj){
		var op = $(obj).attr("op");
		var field = $(obj).attr("field");
		var data = $(obj).val();
		return '{"field":"' + field + '","op":"' + op
				+ '","data":"' + data + '"},';

	},
	
	initQueryData : function(aoData, columnHeader) {
		var sortColn = this.getSortCol(aoData);
		var sortOrder = this.getSortDirect(aoData);
		var sortOrderBy = columnHeader[sortColn];
		var iDisplayStart = aoData[3].value;
		var iDisplayLength = aoData[4].value;
		if (iDisplayLength<0){
			iDisplayLength =999999;
			
		}
		var pageNo = parseInt(iDisplayStart / iDisplayLength, 10) + 1;

		var rules = this.getRules();



		return {
			"pageNo" : pageNo,
			"pageSize" : iDisplayLength,
			"order" : sortOrder,
			"orderBy" : sortOrderBy,
			"filters" : '{"groupOp":"AND","rules":[' + rules + ']}'
		};

	},
	select : function(selector){
		var result  = new Array();
		if (selector){
			selector.find(".forselect").each(
					function(index, value) {
						if ($( this ).is(":checked")){
							result.push($(this).val());
						}
			})
		
		}else{
			$(".forselect").each(
					function(index, value) {
						if ($( this ).is(":checked")){
							result.push($(this).val());
						}
					});
		
			
		}
		return result;
	}
});
  
$.extend(
				$.fn.dataTable.defaults,
				{
					"sDom": "<'col-md-4 for_filter'><'col-md-4'l>rt<'col-md-6'i><'col-md-6'p>>",
 					"aLengthMenu": [[10,25,50,100,300,99999],[10,25,50,100,300,'All']],
  					"sPaginationType" : "full_numbers",
					"bJQueryUI" : false,
					"bAutoWidth" : false,
					"bLengthChange" : false,
					"bFilter" : false,
					"aaSorting" : [ [ 0, "asc" ] ],
					"bProcessing" : true,
					"bServerSide" : true,
					"bStateSave": true,
				    "fnStateSave": function (oSettings, oData) {
				        oData["abVisCols"] = [];
				        localStorage.setItem( 'DataTables_'+window.location.pathname, JSON.stringify(oData) );
				    },
				   "fnStateLoad": function (oSettings) {
				      return JSON.parse( localStorage.getItem('DataTables_'+window.location.pathname) );
				    },
				 
					"fnServerData" : function(sUrl, aoData, fnCallback,
							oSettings) {
 						
						var this_ = $.fn.dataTable;
						var echo = aoData[0].value;
						
						var data;
						if ($.isFunction(oSettings.oInit.createQueryData)){
							data = oSettings.oInit.createQueryData(aoData);
						}else{
							data = new QueryData(aoData);
						}
 						var filter = data["filters"];
						delete data['filters'];
 						oSettings.jqXHR = $
								.ajax( {
									"url" : sUrl+"/"+filter+"/views/",
									"data" : data,
									"success" : function(json) {
										var json;
										if ($.isFunction(oSettings.oInit.createResult)){
											json = oSettings.oInit.createResult(json, echo);
										}else{
											json = new DataTableForm(json, echo);
										}
  										if (json.sError) {
											oSettings.oApi._fnLog(oSettings, 0,
													json.sError);
										}

										$(oSettings.oInstance).trigger('xhr',
												[ oSettings, json ]);
										fnCallback(json);	
  										
										if( $.isFunction( johnny.onDataLoad)) {
												johnny.onDataLoad();	
										}
										
										if( $.isFunction( oSettings.oInit.onDataLoad)) {
											oSettings.oInit.onDataLoad();
										}
										
										
										
									},
									"dataType" : "json",
									"cache" : false,
									"type" : oSettings.sServerMethod,
									"error" : function(xhr, error, thrown) {
										if (error == "parsererror") {
											oSettings.oApi
													._fnLog(
															oSettings,
															0,
															"DataTables warning: JSON data from "
																	+ "server could not be parsed. This is caused by a JSON formatting error.");
										}
									}
								});
					}
				});
