if (!johnny) {
	var johnny = {}
};

$
		.extend(
				true,
				johnny,
				{
					registerLazyTab : function(key, fun) {
						$("a[href='#" + key + "]").click(function(e) {
							if (!$(this).hasClass("inited")) {
								$(this).addClass("inited");
								setTimeout(fun, 400);
							}
						});
					},
					getForSelect : function(value, selected) {
						var mainSpan = $("<span>");
						$("<input>").addClass("col-md-12 styled").attr({
							class : 'forselect ',
							type : "checkbox",
							value : value,
							checked : (selected) ? true : false
						}).appendTo(mainSpan);
						if (selected) {
							mainSpan.addClass("checked");
						}
						var div = $("<div>").addClass("checker").append(
								mainSpan);
						return div[0].outerHTML;

					},
					getGridLink : function(conf) {
						var mainSpan = $("<span>");
						var controlDiv = $("<div>").addClass("controls center")
								.appendTo(mainSpan);
						$(conf).each(
								function(index, value) {
									if (value.sub && value.sub.length > 1) {
										johnny.getSubActionlink(value.css,
												value.sub, value.target)
												.appendTo(controlDiv);

									} else {
										johnny.getActionLink(value.link,
												value.text, value.css,
												value.dataId, value.target)
												.appendTo(controlDiv);
									}

								});
						return mainSpan.html();
					},
					getImageHtml : function(link) {

						var mainSpan = $("<span/>").append(
								$("<div>").append($("<a>", {
									target : "_blank",
									href : link
								}).append($("<img/>").attr({
									src : link
								}))));
						return mainSpan.html();
					},
					getRedirectHtml : function(link) {
						var mainSpan = $("<span/>").append(
								$("<div>").append($("<a>", {
									target : "_blank",
									href : link
								}).text("Click")));
						return mainSpan.html();

					},

					getActionHtml : function(link, title, icon) {
						var mainSpan = $("<span>");
						var controlDiv = $("<div>").addClass("controls center")
								.appendTo(mainSpan);
						johnny.getActionLink(link, title, icon).appendTo(
								controlDiv);
						return mainSpan.html();
					},
					getEditHtml : function(link) {
						return johnny.getActionHtml(link, "edit",
								"icomoon-icon-pencil");
					},

					getInlineHtml : function(link) {
						return johnny.getActionHtml(link, "in-line",
								"icomoon-icon-eyedropper");
					},
					getActionLink : function(link, title, icon, dataId, target) {
						var href = $("<a>").attr({
							href : link,
							title : title,
							dataid : (dataId) ? dataId : "#",
							target : (target) ? target : "_self"
						}).addClass("tip");
						var span = $("<span>").addClass(icon + " icon12")
								.appendTo(href);
						return href;
					},

					getSubActionlink : function(icon, sub, target) {
						var href = $("<a>").attr({
							href : "#",
							"data-toggle" : "dropdown",
						}).addClass("dropdown-toggle");
						var span = $("<span>").addClass(icon + " icon12")
								.appendTo(href);
						var ul = $("<ul>").addClass("dropdown-menu");

						$(sub).each(function(step, value) {
							var li = $('<li>').appendTo(ul);
							var a = $('<a>', {
								href : value.url,
								text : value.name,
								target : (target) ? target : "_self"
							}).appendTo(li);
							$('<span class="i"></span>').appendTo(li);
						});
						var result = $('<div class="btn-group dropup"/>')
								.append(href).append(ul);
						console.dir(result.html());
						return result;
					},

					getInlineAndEdit : function(edit, inline, view) {

						var editHtml = johnny.getActionLink(edit, "edit",
								"icomoon-icon-pencil");

						var mainSpan = $("<span>");
						var controlDiv = $("<div>").addClass("controls center")
								.appendTo(mainSpan);
						if (view != undefined) {
							johnny
									.getActionLink(view, "view",
											"minia-icon-eye").appendTo(
											controlDiv);
						}

						editHtml.appendTo(controlDiv);
						if (inline != null) {
							var inlineHtml = johnny.getActionLink(inline,
									"in line edit", "icomoon-icon-eyedropper");
							inlineHtml.appendTo(controlDiv);
						}
						return mainSpan.html();

					},

					message : function(type, text) {
						if ('warning' == type) {
							johnny.warning(text);
						} else if ('error' == type) {

							johnny.error(text);
						} else if ('info' == type) {
							johnny.info(text);

						} else if ('success' == type) {

							johnny.success(text);
						}
					},
					info : function(text) {
						var icon = 'alert-info';
						var type = 'Info';
						johnny.showNotify(icon, type, text);
					},
					success : function(text) {
						var icon = 'alert-success';
						var type = 'Success';
						johnny.showNotify(icon, type, text);
					},
					error : function(text) {
						var icon = 'alert-danger';
						var type = 'Error';
						johnny.showNotify(icon, type, text);
					},
					warning : function(text) {
						var icon = 'alert-warning';
						var type = 'Warning';
						johnny.showNotify(icon, type, text);
					},
					showNotify : function(icon, type, text) {
						var width = $(window).width();
						var div = $("<div>")
								.css({
									top : '30px',
									width : width / 2,
									left : width / 4
								})
								.addClass("message-alert alert")
								.addClass(icon)
								.append($("<b>").text(type))
								.append(
										$("<p>")
												.text(text)
												.append(
														$("<button>")
																.addClass(
																		"close")
																.attr(
																		"data-dismiss",
																		"alert")
																.attr(
																		"data-auto-dismiss",
																		"5")
																.text("x")));

						$("body").append(div);
						setTimeout(function(){ div.remove() }, 3000);

					},
					initColumnHeader : {},
					initTableQuery : function(name, container) {
						if (name) {
							var searchLabel = $("<label>").html("Search:")
									.append(name)
							// var filterDiv = $("<div>");

							if (container) {
								var x = $(container).find('.for_filter');
								if (x.length > 0) {
									x.append(filterDiv);
								} else {
									$('.dataTables_wrapper').addClass(
											"dataTables_filter").append(
											searchLabel)
								}

							} else {
								var x = $('.for_filter');
								if (x.length > 0) {
									x.addClass("dataTables_filter").append(
											searchLabel);
								} else {
									$('.dataTables_wrapper').addClass(
											"dataTables_filter").append(
											searchLabel)
								}
							}

						}

						johnny.resetFilter($('.reset'));
						$(".datepickerlazy").datepicker({
							dateFormat : 'yy-mm-dd',
							onSelect : function() {
								johnny.updateGrid();
							}
						}).keydown(function(event) {
							if (event.keyCode == 8) {
								$(".datepickerlazy").val("");
							} else {
								event.preventDefault();
							}
						});
						$(".filterdata").keyup(function() {
							johnny.updateGrid();
						});

						$(".filterdata").change(function() {
							johnny.updateGrid();
						});
					},
					oTime : {},
					oTable : {},
					updateGrid : function() {
						window.clearTimeout(johnny.oTime);
						johnny.oTime = setTimeout(function() {
							johnny.oTable.fnDraw();
						}, 500);
					},
					tableBtn : function(config) {
						var span
						if (config.selector) {
							span = $(config.selector);

						} else {
							span = $('.header > span.right');
						}
						if (span.length) {

						} else {

							var bar = $('.header');
							if (bar.length > 1) {

								var button = $('.header');
								if (button.length == 1) {
									bar = button;
								}
							} else {

							}
							span = $('<span/>', {
								"class" : 'box-form right',
							}).appendTo(bar);
						}

						$('<a/>', {
							href : '#',
							id : config.id,
							class : 'btn btn-default btn-clean',
						}).text(config.text).appendTo(span);

						$('#' + config.id)
								.click(
										function() {

											var items = johnny
													.select(config.selects);
											if (items && items.length <= 0) {
												johnny
														.warning("Please select at least one "
																+ config.domain
																+ " for "
																+ config.operation);
												return false;
											}
											if (config.max) {
												if (items.length > config.max) {
													johnny
															.warning("Only "
																	+ config.max
																	+ " items allowed for "
																	+ config.operation);
													return false;

												}

											}

											var dialog = $("#confirm-dialog");
											dialog
													.find(".btn-success")
													.bind(
															"click",
															function() {
																$(this)
																		.unbind(
																				"click");
																$
																		.ajax({
																			url : config.url,
																			type : "POST",
																			data : {
																				"_method" : "PUT",
																				"ids" : items
																			},
																			dataType : "json",
																			success : function(
																					data) {
																				if (data
																						&& data.state
																						&& (data.state
																								.indexOf("ERROR") != -1)) {
																					johnny
																							.error(data.message);
																					return;
																				}
																				johnny
																						.success(config.operation
																								+ " "
																								+ config.domain
																								+ " successfully");
																				if (config.selects) {
																					config.selects
																							.dataTable()
																							.fnDraw();
																				} else {
																					johnny
																							.updateGrid();
																				}
																			}
																		});
															});

											dialog.modal("show");
											// dialog.find(".btn-danger").bind(
											// "click", function() {
											// 			dialog.unbind("click");
											// 		});

											return false;
										});

					}
				});

$(function() {

	var selected = "icon-ok";
	var unSelected = "icon-remove";

	$("table").delegate(".forselect", "click", function() {
		$(this).parents("span:first").toggleClass("checked");

	});

	if (("#select_all , .select_all").length != 0) {

		$("#select_all , .select_all").click(function() {
			var click = $(this);
			var table = click.parents("table:first");
			var check = (table.find('.' + selected).length == 1);
			table.find('.forselect').each(function(index) {
				if (check) {
					$(this).prop('checked', check);
					$(this).attr('checked', "checked");
					$(this).parents("span:first").addClass("checked");
					click.removeClass(selected).addClass(unSelected);
				} else {
					$(this).prop('checked', check);
					console.dir($(this));
					$(this).removeAttr('checked');
					$(this).parents("span:first").removeClass("checked");
					click.removeClass(unSelected).addClass(selected);
				}
			});

		});

	}

	$
			.ajaxSetup({
				error : function(jqXHR, exception) {
					if (jqXHR.status === 0) {
						johnny.error('Not connect. Please verify network.');
					} else if (jqXHR.status == 401) {
						johnny
								.error('No permission to view that page. \nMaybe because of your session timeout.');
					} else if (jqXHR.status == 404) {
						johnny.error('Requested page not found.');
					} else if (jqXHR.status == 500) {
						johnny
								.error('Internal Server Error. \nPlease inform IT Support if it happends again.');
					} else if (exception === 'parsererror') {
						johnny.error('Requested JSON parse failed.');
					} else if (exception === 'timeout') {
						johnny.error('Time out error.');
					} else if (exception === 'abort') {
						johnny.error('Ajax request aborted.');
					} else {
						johnny.error('Uncaught Error.\n' + jqXHR.responseText);
					}
				},
				statusCode : {
					500 : function(xhr) {
						johnny
								.warning("Internal Server Error. \nPlease inform IT Support if it happends again.");
					},
					401 : function(xhr) {
						johnny
								.warning("No permission to view that page. \nMaybe because of your session timeout.");
					}
				}
			});

});