<script type='text/javascript' src='js/plugins/flot/jquery.flot.js'></script>     
<script type='text/javascript' src='js/plugins/flot/jquery.flot.resize.js'></script>
<script type='text/javascript' src='js/charts.js'></script>

<script type="text/javascript" th:inline="javascript">
/*<![CDATA[*/
	$(function() {
		var data = [],
			totalPoints = 300;
		function getRandomData() {
			if (data.length > 0)
				data = data.slice(1);
			while (data.length < totalPoints) {
				var prev = data.length > 0 ? data[data.length - 1] : 50,
					y = prev + Math.random() * 10 - 5;
				if (y < 0) {
					y = 0;
				} else if (y > 100) {
					y = 100;
				}
				data.push(y);
			}
			var res = [];
			for (var i = 0; i < data.length; ++i) {
				res.push([i, data[i]])
			}
			return res;
		}
		var updateInterval = 100;
		$("#updateInterval").val(updateInterval).change(function () {
			var v = $(this).val();
			if (v && !isNaN(+v)) {
				updateInterval = +v;
				if (updateInterval < 1) {
					updateInterval = 1;
				} else if (updateInterval > 2000) {
					updateInterval = 2000;
				}
				$(this).val("" + updateInterval);
			}
		});

		var plot = $.plot("#placeholder", [ getRandomData() ], {
			series: {
				shadowSize: 0
			},
			yaxis: {
				min: 0,
				max: 100
			},
			xaxis: {
				show: false
			}
		});

		function update() {

			plot.setData([getRandomData()]);
			plot.draw();
			setTimeout(update, updateInterval);
		}
		update();
		 
	});
	/*]]>*/
	</script>


      <div class="row">
             
            <div class="col-md-12">
                
                <div class="block block-transparent">
                    <div class="header">
                        <h2>Line chart</h2>
                    </div>
                    <div class="content">
                        <div id="chart_line_1" style="width: 100%; height: 250px;"></div>
                    </div>
                </div>
                
                <div class="block block-transparent">
                    <div class="header">
                        <h2>Line chart</h2>
                    </div>
                    <div class="content">
                        <div id="placeholder" style="width: 100%; height: 250px;"></div>
                    </div>
                </div>
                
                    
               
                <div class="block block-transparent">
                    <div class="header">
                        <h2>On/Off series</h2>                        
                    </div>
                    <div class="content controls">
                        
                        <div class="col-md-9">
                            <div id="chart_series_onoff" style="height: 290px; width: 100%;"></div>
                        </div>
                        <div class="col-md-3">
                            <div class="block block-transparent">
                                <div class="content controls" id="choices">
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <div class="checkbox">
                                                <input type="checkbox" name="usa" id="idusa"/> USA
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <div class="checkbox">
                                                <input type="checkbox" name="russia" checked="checked" id="idrussia"/> Russia
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <div class="checkbox">
                                                <input type="checkbox" name="uk" checked="checked" id="iduk"/> UK
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <div class="checkbox">
                                                <input type="checkbox" name="germany" checked="checked" id="idgermany"/> Germany
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <div class="checkbox">
                                                <input type="checkbox" name="denmark" checked="checked" id="iddenmark"/> Denmark
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <div class="checkbox">
                                                <input type="checkbox" name="sweden" checked="checked" id="idsweden"/> Sweden
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <div class="checkbox">
                                                <input type="checkbox" name="norway" checked="checked" id="idnorway"/> Norway
                                            </div>
                                        </div>
                                    </div>                    
                                </div>
                            </div>
                        </div>                        
                        
                    </div>
                </div>
                
            </div>
        </div>