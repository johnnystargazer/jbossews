
<script type='text/javascript' src='/js/plugins/jquery/globalize.js'></script>
<script type='text/javascript'
	src='/js/plugins/datatables/jquery.dataTables.min.js'></script>
<script type="text/javascript" src="/js/datatable-query.js"></script>
<script type="text/javascript" src="/js/message.js"></script>
<div class="row">
	<div class="col-md-12">
		<div class="block">
			<div class="header">
				<h2>Sortable table</h2>
				<span class="right"></span>

			</div>

			<div class="content">
				<table class="ajaxTable table-striped table table-bordered">
					<thead>
						<tr>
							<th><a class="select_all icon-ok "></a></th>
							<th>Time</th>
							<th>User</th>
							<th>Source</th>
							<th>Content</th>
 							<th>Operation</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/*<![CDATA[*/
		var render = false;

		$(document).ready(function() {
			johnny.oTable = $('.ajaxTable').dataTable({
				"aoColumnDefs" : [ {
					'bSortable' : false,
					'aTargets' : [ 0 ]
				} ],
				"bLengthChange" : true,
				"sAjaxSource" : '/message/',
				"fnInitComplete" : function(oSettings, json) {
				}
			});
			var name = $("<input>").attr({
				type : "text",
				field : "content",
				op : "cn",
				placeholder : "Content",
				class : "filterdata"
			});
			johnny.initTableQuery(name);

			var del = {
				id : 'messageDel',
				text : 'Del',
				domain : "Message",
				operation : "Del",
				url : "/message"
			};
			johnny.tableBtn(del);
		});

		var columnHeader = [ '', 'time', 'user', 'source', 'content' ];
		var QueryData = function(aoData) {
			return johnny.initQueryData(aoData, columnHeader);
		}

		var DataTableForm = function(json, echo) {
			var dataArray = new Array();
			for (var i = 0; i < json.result.length; i++) {
				var link = "${ctx}/admin/message/" + json.result[i].id;
				var subArray = new Array();
				var value = johnny.getForSelect(json.result[i].id);
				subArray.push(value);
				subArray.push(json.result[i].time)
				subArray.push(json.result[i].user)
				subArray.push(json.result[i].source)
				subArray.push(json.result[i].content)

				subArray.push(johnny.getEditHtml(link));
				dataArray.push(subArray);
			}
			return {
				sEcho : echo,
				iTotalRecords : json.totalCount,
				iTotalDisplayRecords : json.totalCount,
				aaData : dataArray
			};
		}

		/*]]>*/
	</script>
	<a href="/socket-client" class="btn btn-info"> <span
		class="icon12 icomoon-icon-plus white"></span> Chat
	</a>
</div>
