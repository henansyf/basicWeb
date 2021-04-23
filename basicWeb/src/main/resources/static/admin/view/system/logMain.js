

var log_store = Ext.create('Ext.data.Store', {
     pageSize : pageSize,
     proxy: {
         type: 'ajax',
		 url : 'aSystemLog/list.do',
         reader: {
             type: 'json',
             root: 'result',
             totalProperty : 'totalCount',
             idProperty : 'id'
         }
     },
      fields: []
 });

Ext.define('admin.view.system.logMain', {
			extend : "Ext.panel.Panel",
			layout : "border",
			initComponent : function() {
				var me=this;
				me.items = [Ext.create('Ext.panel.Panel', {
									region : "north",
									layout : 'column',
									bodyPadding : 5,
									defaults : {
										labelAlign : 'right',
										labelWidth : 110
									},
									bodyBorder : true,
									tbar : [{
										xtype : 'button',
										style : 'font-size:12px',
										text : '查询',
										icon : "images/admin/icon_04.png",
										handler:function(){	
											log_store.loadPage(1);
										}
									}],
									items : [{
													xtype : 'textfield',
													fieldLabel : "操作用户",
													labelWidth : 80,
													width : 180,
													labelAlign : 'right',
													id : 'lm_user'
												}, {
													xtype : 'datefield',
													fieldLabel : "操作日期",
													labelWidth : 80,
													labelAlign : 'right',
													name : 'startTime',
													id : 'lm_startTime',
													format : 'Y-m-d',
													editable : true,
													width : 200
												}, {
													xtype : 'datefield',
													fieldLabel : "至",
													labelAlign : 'right',
													labelWidth : 20,
													matchFieldWidth : false,
													name : 'endTime',
													id : 'lm_endTime',
													format : 'Y-m-d',
													editable : true,
													width : 140
												}, {
													xtype : 'textfield',
													fieldLabel : "日志内容",
													labelWidth : 80,
													width : 220,
													labelAlign : 'right',
													id : 'lm_content'
												}]
								}),Ext.create('Ext.grid.Panel', {
									region : "center",
									extend : 'Ext.grid.Panel',
									border : true,
									loadMask : true,
									store:log_store,
									dockedItems:Ext.create('Ext.toolbar.Paging',{
							    		store : log_store, 
							    		displayInfo:true, 
							            dock : 'bottom', 
							            emptyMsg:'没有相关数据',
							            listeners: {
							                afterrender : function() {
							                    this.child('#refresh').hide();
							                }
							            }
							    	}),
							    	columns : [ new Ext.grid.RowNumberer({header:'序号',width:60}),  
									    {header:'操作时间',dataIndex:'createTime',width : 150,sortable:true},
									    {header:'操作用户',dataIndex:'operater',width : 100,sortable:true},
									    {header:'日志内容',dataIndex:'operLog',width : 600,sortable:true,renderer : function(vl, metaData, re) {
												metaData.tdAttr = 'data-qtip=\"'
														+ Ext.String.htmlEncode(vl) + '\"';
												return vl;
										  }}
									    ]
									}
								)];
				me.callParent(arguments);
				log_store.on('beforeload', function() {
		          log_store.getProxy().extraParams={
		             logContent:Ext.getCmp('lm_content').getValue(),
		              logOperater:Ext.getCmp('lm_user').getValue(),
		               startTime:Ext.getCmp('lm_startTime').getValue(),
		                endTime:Ext.getCmp('lm_endTime').getValue()
		          };
		      });
				log_store.loadPage(1);
			}
		});
