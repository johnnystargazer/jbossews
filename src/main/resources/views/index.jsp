<script type='text/javascript' src='/js/plugins/flot/jquery.flot.js'></script>     
<script type='text/javascript' src='/js/plugins/flot/jquery.flot.resize.js'></script>
<script type='text/javascript' src='/js/charts.js'></script>
    
	<div class="row">
		<div class="col-md-6">

			<div class="block block-drop-shadow">
				<div class="head bg-default bg-light-ltr">
					<h2>Total sales</h2>
					<div class="side pull-right">
						<ul class="buttons">
							<li><a href="#"><span class="icon-cogs"></span></a></li>
						</ul>
					</div>
					<div class="head-panel nm">
						<div class="left_abs_100" style="margin-top: 20px;">
							<div class="knob">
								<input type="text" data-fgColor="#FFFFFF" data-min="0"
									data-max="200" data-width="100" data-height="100" value="155"
									data-readOnly="true" />
							</div>
						</div>
						<div class="chart" id="dash_chart_1" style="height: 150px;"></div>
					</div>
					<div class="head-panel nm">
						<div class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-thumbs-up-alt"></span>
							</div>
							<span class="hp-main">155</span> <span class="hp-sm">Sales</span>
						</div>
						<div class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-thumbs-down-alt"></span>
							</div>
							<span class="hp-main">23</span> <span class="hp-sm">Cancelled</span>
						</div>
						<div class="hp-info pull-right">
							<div class="hp-icon">
								<span class="icon-usd"></span>
							</div>
							<span class="hp-main">19,215.23</span> <span class="hp-sm">Total
								Income</span>
						</div>
					</div>
				</div>
				<div class="content list">
					<div class="list-title">Previous months</div>
					<div class="list-item">
						<div class="list-text">
							<strong>May 2013</strong>
							<div class="progress progress-small">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"
									style="width: 75%"></div>
							</div>
						</div>
					</div>
					<div class="list-item">
						<div class="list-text">
							<strong>April 2013</strong>
							<div class="progress progress-small">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="64" aria-valuemin="0" aria-valuemax="100"
									style="width: 64%"></div>
							</div>
						</div>
					</div>
					<div class="list-item">
						<div class="list-text">
							<strong>March 2013</strong>
							<div class="progress progress-small">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="58" aria-valuemin="0" aria-valuemax="100"
									style="width: 58%"></div>
							</div>
						</div>
					</div>
					<div class="list-item">
						<div class="list-text">
							<strong>February 2013</strong>
							<div class="progress progress-small">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="72" aria-valuemin="0" aria-valuemax="100"
									style="width: 72%"></div>
							</div>
						</div>
					</div>
					<div class="list-item">
						<div class="list-text">
							<strong>January 2013</strong>
							<div class="progress progress-small">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="83" aria-valuemin="0" aria-valuemax="100"
									style="width: 83%"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="footer footer-defaut tac">
					<div class="pull-left" style="width: 200px;">
						<div class="input-group">
							<div class="input-group-addon">
								<span class="icon-calendar"></span>
							</div>
							<input type="text" class="datepicker form-control"
								value="10/08/2013" />
							<div class="input-group-btn">
								<button class="btn">
									<span class="icon-search"></span>
								</button>
							</div>
						</div>
					</div>
					<div class="pull-right">
						<a href="#">More information</a>
					</div>
				</div>
			</div>

			<div class="block block-drop-shadow">

				<div class="head bg-dot30">
					<h2>This week visits</h2>
					<div class="side pull-right">
						<ul class="buttons">
							<li><a href="#"><span class="icon-refresh"></span></a></li>
							<li><a href="#"><span class="icon-cogs"></span></a></li>
						</ul>
					</div>
					<div class="head-panel">
						<div class="chart" id="dash_chart_2" style="height: 150px;"></div>
					</div>
				</div>

				<div class="head bg-dot30">
					<div class="head-panel nm">
						<div class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-globe"></span>
							</div>
							<span class="hp-main">12,480</span> <span class="hp-sm">Total
								visits</span>
						</div>
						<div class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-desktop"></span>
							</div>
							<span class="hp-main" style="margin-left: 35px;">10,140</span> <span
								class="hp-sm" style="margin-left: 35px;">Desktop</span>
						</div>
						<div class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-laptop"></span>
							</div>
							<span class="hp-main" style="margin-left: 35px;">1,204</span> <span
								class="hp-sm" style="margin-left: 35px;">Desktop</span>
						</div>
						<div class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-tablet"></span>
							</div>
							<span class="hp-main">322</span> <span class="hp-sm">Tablet</span>
						</div>
						<div class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-mobile-phone"></span>
							</div>
							<span class="hp-main">814</span> <span class="hp-sm">Mobile</span>
						</div>
					</div>
				</div>

				<div class="head bg-dot30">
					<h2>Visits by browser</h2>
					<div class="head-panel nm">
						<div class="progress">
							<div class="progress-bar progress-bar-success tip" title="Chrome"
								style="width: 35%"></div>
							<div class="progress-bar progress-bar-info tip" title="Firefox"
								style="width: 20%"></div>
							<div class="progress-bar progress-bar-warning tip" title="Opera"
								style="width: 20%"></div>
							<div class="progress-bar progress-bar-danger tip" title="Safari"
								style="width: 10%"></div>
							<div class="progress-bar tip" title="Internet Explorer"
								style="width: 15%"></div>
						</div>
					</div>
				</div>

			</div>

		</div>

		<div class="col-md-6">

			<div class="block block-drop-shadow">
				<div class="head bg-default bg-light-rtl">
					<h2>Inbox messages</h2>
					<div class="side pull-right">
						<ul class="buttons">
							<li><a href="#"><span class="icon-plus"></span></a></li>
							<li><a href="#"><span class="icon-cogs"></span></a></li>
						</ul>
					</div>
					<div class="head-panel nm">
						<a href="#" class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-download-alt"></span>
							</div> <span class="hp-main">25</span> <span class="hp-sm">recived</span>
						</a> <a href="#" class="hp-info pull-left">
							<div class="hp-icon">
								<span class="icon-upload-alt"></span>
							</div> <span class="hp-main">10</span> <span class="hp-sm">sent</span>
						</a> <a href="#" class="hp-info hp-one pull-right tip" title="Refresh">
							<div class="hp-icon">
								<span class="icon-refresh"></span>
							</div> <span class="hp-main">9:24 am</span>
						</a>
					</div>
				</div>
				<div class="content list">
					<div class="list-item">
						<div class="list-datetime">
							<div class="time">9:45 am</div>
						</div>
						<div class="list-info">
							<img src="img/example/user/dmitry.jpg"
								class="img-circle img-thumbnail" />
						</div>
						<div class="list-text">
							<a href="#" class="list-text-name">John Doe</a>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Pellentesque condimentum nisl velit.</p>
						</div>
						<div class="list-controls">
							<a href="#" class="widget-icon widget-icon-circle"><span
								class="icon-rotate-right"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-pushpin"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-remove"></span></a>
						</div>
					</div>
					<div class="list-item">
						<div class="list-datetime">
							<div class="time">8:16 am</div>
						</div>
						<div class="list-info">
							<img src="img/example/user/alexey.jpg"
								class="img-circle img-thumbnail" />
						</div>
						<div class="list-text">
							<a href="#" class="list-text-name">Brad Pitt</a>
							<p>Duis eu libero pellentesque, dapibus ante eu, vehicula
								leo. Nulla gravida rutrum neque.</p>
						</div>
						<div class="list-controls">
							<a href="#" class="widget-icon widget-icon-circle"><span
								class="icon-rotate-right"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-pushpin"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-remove"></span></a>
						</div>
					</div>
					<div class="list-item">
						<div class="list-datetime">
							<div class="date">27.08</div>
							<div class="time">9:59 pm</div>
						</div>
						<div class="list-info">
							<img src="img/example/user/olga.jpg"
								class="img-circle img-thumbnail" />
						</div>
						<div class="list-text">
							<a href="#" class="list-text-name">Angelina Jolie</a>
							<p>Morbi tincidunt, tellus ut fermentum accumsan, est justo
								pretium enim, eget.</p>
						</div>
						<div class="list-controls">
							<a href="#" class="widget-icon widget-icon-circle"><span
								class="icon-rotate-right"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-pushpin"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-remove"></span></a>
						</div>
					</div>
					<div class="list-item">
						<div class="list-datetime">
							<div class="date">27.08</div>
							<div class="time">4:34 pm</div>
						</div>
						<div class="list-info">
							<img src="img/example/user/helen.jpg"
								class="img-circle img-thumbnail" />
						</div>
						<div class="list-text">
							<a href="#" class="list-text-name">Keira Knightley</a>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Pellentesque condimentum nisl velit.</p>
						</div>
						<div class="list-controls">
							<a href="#" class="widget-icon widget-icon-circle"><span
								class="icon-rotate-right"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-pushpin"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-remove"></span></a>
						</div>
					</div>
					<div class="list-item">
						<div class="list-datetime">
							<div class="date">26.08</div>
							<div class="time">12:12 am</div>
						</div>
						<div class="list-info">
							<img src="img/example/user/dmitry.jpg"
								class="img-circle img-thumbnail" />
						</div>
						<div class="list-text">
							<a href="#" class="list-text-name">John Doe</a>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Pellentesque condimentum nisl velit.</p>
						</div>
						<div class="list-controls">
							<a href="#" class="widget-icon widget-icon-circle"><span
								class="icon-rotate-right"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-pushpin"></span></a> <a href="#"
								class="widget-icon widget-icon-circle"><span
								class="icon-remove"></span></a>
						</div>
					</div>
				</div>
				<div class="footer tac">
					<a href="#">Load more messages...</a>
				</div>
			</div>

			<div class="block block-drop-shadow">
				<div class="header">
					<h2>Messaging</h2>
					<div class="side pull-right">
						<ul class="buttons">
							<li><a href="#"><span class="icon-user"></span></a></li>
							<li><a href="#"><span class="icon-cogs"></span></a></li>
						</ul>
					</div>
				</div>
				<div class="content messages npr npb">
					<div class="scroll oh" style="height: 250px;">
						<div class="messages-item">
							<img src="img/example/user/dmitry_s.jpg"
								class="img-circle img-thumbnail" />
							<div class="messages-item-text">Duis eu libero
								pellentesque, dapibus ante eu, vehicula leo. Nulla gravida
								rutrum neque</div>
							<div class="messages-item-date">14:33 30.08.2013</div>
						</div>
						<div class="messages-item inbox">
							<img src="img/example/user/olga_s.jpg"
								class="img-circle img-thumbnail" />
							<div class="messages-item-text">Lorem ipsum dolor sit amet,
								consectetur adipiscing elit. Pellentesque condimentum nisl velit</div>
							<div class="messages-item-date">14:32 30.08.2013</div>
						</div>
						<div class="messages-item">
							<img src="img/example/user/dmitry_s.jpg"
								class="img-circle img-thumbnail" />
							<div class="messages-item-text">Duis eu libero
								pellentesque, dapibus ante eu, vehicula leo. Nulla gravida
								rutrum neque</div>
							<div class="messages-item-date">14:20 30.08.2013</div>
						</div>
						<div class="messages-item inbox">
							<img src="img/example/user/olga_s.jpg"
								class="img-circle img-thumbnail" />
							<div class="messages-item-text">Lorem ipsum dolor sit amet,
								consectetur adipiscing elit. Pellentesque condimentum nisl velit</div>
							<div class="messages-item-date">14:19 30.08.2013</div>
						</div>
						<div class="messages-item">
							<img src="img/example/user/dmitry_s.jpg"
								class="img-circle img-thumbnail" />
							<div class="messages-item-text">Duis eu libero
								pellentesque, dapibus ante eu, vehicula leo. Nulla gravida
								rutrum neque</div>
							<div class="messages-item-date">14:15 30.08.2013</div>
						</div>
						<div class="messages-item inbox">
							<img src="img/example/user/olga_s.jpg"
								class="img-circle img-thumbnail" />
							<div class="messages-item-text">Lorem ipsum dolor sit amet,
								consectetur adipiscing elit. Pellentesque condimentum nisl velit</div>
							<div class="messages-item-date">14:10 30.08.2013</div>
						</div>
					</div>
				</div>
				<div class="footer">
					<div class="input-group">
						<span class="input-group-addon"><i class="icon-comment"></i></span>
						<input type="text" class="form-control" placeholder="message.." />
						<span class="input-group-btn">
							<button class="btn">
								<span class="icon-chevron-up"></span>
							</button>
						</span>
					</div>
				</div>
			</div>



		</div>

	</div>
 