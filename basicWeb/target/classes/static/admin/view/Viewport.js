Ext.define('admin.view.Viewport',{
	extend: 'Ext.Viewport', 
	alias: 'widget.viewport',//命名可以方便用create()
	layout: 'border',
	hideBorders: true,
	requires : [
	    		'admin.view.MenuView',
	    		'admin.view.TabPanelView'
	    	],
	initComponent : function(){
        Ext.apply(this, {
         /*   items: [{
            	id:'desk',
				layout: 'border',*/
				items: [new Ext.Component({
					region : 'north',
					height : 60,
					html:'<iframe scrolling="no" frameborder="0" width="100%" height="100%" src="alogin/head"></iframe>'
				}),
				Ext.create('widget.menuView'),
				Ext.create('widget.tabPanelView')
				]
			//}]
        });
        this.callParent(arguments);
	}
});
