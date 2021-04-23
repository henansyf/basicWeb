Ext.define("admin.view.TabPanelView", {
	extend : "Ext.tab.Panel",
	alias : "widget.tabPanelView",
	initComponent : function() {
		Ext.apply(this, {
			id : 'tabpanel',
			region : 'center',
			width : '100%',
			autoHeight : true,
			defaults : {
				autoScroll : true
			},
			activeTab : 0,
			border : false,
			items : [{
				id : 'HomePage',
				title : '首页',
				layout : 'fit',
				html : '<iframe scrolling="no" frameborder="0" width="100%" height="99.99%" src="alogin/center"></iframe>'
			}]
			
		});
		this.callParent(arguments);
	}
});