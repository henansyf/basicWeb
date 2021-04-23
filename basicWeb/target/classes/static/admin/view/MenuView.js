Ext.define("admin.view.MenuView", {
			extend : "Ext.panel.Panel",
			alias : "widget.menuView",
			title : '功能菜单',
			region : 'west',
			icon:'images/admin/user_main.png',
			width : 200,
			bodyStyle : {
				background : '#f1f1f1'
			},
			split : true,
			collapsible : true,
			layout : {
				type : 'accordion',
				animate : true,
				titleCollapse : true
			},
			listeners : {
				beforerender : makeItems
			}
		});

function makeItems(obj) {
	Ext.Ajax.request({
		url : "alogin/getUrl",// 获取面板的地址
		async : false,// 同步
		params : {
			action : "list"
		},
		timeout:30000,
		method : 'post', // 方法
		callback : function(options, success, response) {
			var data = Ext.JSON.decode(response.responseText);
			for (var i = 0; i < data.length; i++) {
				obj.add(Ext.create("Ext.tree.Panel", {
					title : data[i].text,
					icon : data[i].icon,
					frame:false,
					autoScroll : true,
					rootVisible : false,
					viewConfig : {
						loadingText : "正在加载..."
					},
					store : Ext.create("admin.store.MenuStore", {
								defaultRootId : data[i].id
							}),
					listeners : {
						afterlayout : function() {
							if (this.getView().el) {
								var el = this.getView().el;
								var table = el.down("table.x-grid-table");
								if (table) {
									table.setWidth(el.getWidth());
								}
							}
						},
						itemclick : function(view, node) {
							var tab = Ext.getCmp('V' + node.data.id);
							var tabs = Ext.getCmp('tabpanel');
							if (!tab) {
								if (node.isLeaf()) { // 判断是否是根节点
									if (node.data.type === 'URL') { // 判断资源类型
										var panel = Ext.create(
												'Ext.panel.Panel', {
													title : node.data.text,
													id : 'V' + node.data.id,
													closable : true,
													iconCls : 'icon-activity',
													html : '<iframe width="100%" height="100%" frameborder="0" src="http://www.baidu.com"></iframe>'
												});
										tabs.add(panel);
										tabs.setActiveTab(panel);
										return;
									} else if (node.data.type === 'COMPONENT') {
										var panel = Ext.create(node.data.url, {
													id : 'V' + node.data.id,
													title : node.data.text,
													closable : true,
													iconCls : 'icon-activity'
												});
										tabs.add(panel);
										tabs.setActiveTab(panel);
										return;
									}
								}
							} else {
								tabs.setActiveTab(tab);
							}

						}
					}
				}));
			}
		}
	});
}