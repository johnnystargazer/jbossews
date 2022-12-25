<html xmlns:th="http://www.thymeleaf.org"
	xmlns:tiles="http://www.thymeleaf.org">
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<div tiles:include="header"></div>

<body class="bg-img-num1">

	<div class="page-container">
		<div class="page-sidebar">
			<div class="page-navigation-panel logo"></div>
			<div class="page-navigation-panel">
				<div class="name">Hello </div>
				<div class="control">
					<a href="#" class="psn-control"><span class="icon-reorder"></span></a>
				</div>
			</div>
		<!-- 		
			<div class="page-navigation-panel search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search..." />
					<div class="input-group-addon">
						<span class="icon-search"></span>
					</div>
				</div>
			</div>
			 -->
			<ul class="page-navigation">
				<li><a href="/"> <span class="icon-home"></span>
						Dashboard
				</a></li>
				<li><a href="#"><span class="icon-pencil"></span> Charts</a>
					<ul>
						<li><a href="/charts">Form elements</a></li>
 
					</ul></li>

					 

			</ul>

			<div class="block block-drop-shadow page-navigation-hide">
				<div class="head bg-dot20">
					<h2>Memory</h2>
					<div class="side pull-right">
						<ul class="buttons">
							<li><a href="#"><span class="icon-cogs"></span></a></li>
						</ul>
					</div>
					<div class="head-subtitle">Kingston 2x8Gb DDR3 1866MHz</div>
					<div class="head-panel nm tac">
						<div class="sparkline">
							<span sparkType="pie" sparkWidth="100" sparkHeight="100">5079,3768,7537</span>
						</div>
					</div>
					<div class="head-panel nm">
						<div class="hp-info hp-simple pull-left hp-inline">
							<span class="hp-main"><span class="icon-circle"></span>
								Allocated 5079 Mb [ 31% ]</span>
						</div>
						<div class="hp-info hp-simple pull-left hp-inline">
							<span class="hp-main"><span class="icon-circle text-info"></span>
								In Cache 3768 Mb [ 23% ]</span>
						</div>
						<div class="hp-info hp-simple pull-left hp-inline">
							<span class="hp-main"><span
								class="icon-circle text-primary"></span> Aviable 7537 Mb [ 46% ]</span>
						</div>
					</div>
				</div>
			</div>
			<div class="block block-drop-shadow page-navigation-hide">
				<div class="head bg-dot20">
					<h2>Volumes status</h2>
					<div class="side pull-right">
						<ul class="buttons">
							<li><a href="#"><span class="icon-cogs"></span></a></li>
						</ul>
					</div>
					<div class="head-subtitle">WD Caviar Blue 1TB</div>
					<div class="head-panel nm tac" style="line-height: 0px;">
						<div class="knob">
							<input type="text" data-fgColor="#3F97FE" data-min="0"
								data-max="1024" data-width="100" data-height="100" value="654"
								data-readOnly="true" />
						</div>
					</div>
					<div class="head-panel nm">
						<div class="hp-info hp-simple pull-left hp-inline">
							<span class="hp-main">Volume 1 [ 0.5 TB ]</span> <span
								class="hp-sm">Used: 450.0 GB [ 90% ] </span> <span class="hp-sm">Free:
								50.0 GB [ 10% ] </span>
						</div>
						<div class="hp-info hp-simple pull-left hp-inline">
							<span class="hp-main">Volume 2 [ 0.5 TB ]</span> <span
								class="hp-sm">Used: 154.0 GB [ 30% ] </span> <span class="hp-sm">Free:
								346.0 GB [ 70% ] </span>
						</div>
					</div>
				</div>

			</div>

		</div>
		<div class="page-content">
			<div class="container">
				<div tiles:include="side"></div>
			</div>
		</div>
		<div tiles:include="footer"></div>


	</div>
   <div class="modal" id="confirm-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">                
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Do you want to continue?</h4>
                </div>                
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-clean" data-dismiss="modal">Yes</button>
                    <button type="button" class="btn btn-danger btn-clean" data-dismiss="modal">No</button>
                </div>
            </div>
        </div>
    </div>    
</body>
</html>
