

var roleMainOper = {
	renderOp: function(v, p, record) {
		str = "<a href='javascript:void(0);'  onclick='roleMainOper.edit("+record.get('id')+")'>"+'编辑'+"</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);'  onclick='roleMainOper.del(" + record.get('id') + ")'>"+'删除'+"</a>";
		return str;
	}
	,edit: function(id) {
		 var url='aRole/save';
         var formPanel =Ext.create('Ext.form.Panel',{
         	    bodyPadding:10, 
                width : 800,
				height : 660,
				scrollable:true,
				defaults : {
					labelAlign : 'right',
				    labelWidth :80,
					labelStyle :'font-weight:bold'//,
				   // margin : "(0 0 10 0)"
			 	},
         		items:[{
		                xtype:'hidden',
		                name: 'id'
		            },{
			        xtype: 'textfield',
			        name: 'roleName',
			        fieldLabel: '<font color="red">*</font>角色名称',
			        width:400,
			        allowBlank: false  // 表单项非空
			    },{
			     xtype: 'textareafield',
		         width:400,
		         name: 'roleDesc',
		         fieldLabel: '描述'
			    },{
			    	xtype : "label",
			        style :'font-weight:bold',
			        margin : "(0 5 10 5)",
					text : '选择功能点:'
				},{
					xtype : "button",
					text: '全选',
					width:100,
					handler: function() {
					  var flag=false;
					  if(this.getText()=='全选'){
					  	this.setText("取消全选");
					    flag=true;
					  }else{
					  	 this.setText("全选");
					  }
					  for(var i=1;i<2;i++){
					  	var array = Ext.getCmp('checkboxr'+i).items;
    					array.each(function(item){
    		 				 item.setValue(flag);
    					});
					  }	
            		}
				},  { 
	     	xtype: "checkboxgroup",  
	     	fieldLabel: "系统管理 ", 
	     	id:'checkboxr1',
	     	columns: 3, 
	     	vertical: true, 
	     	items: [ { 
	     	   boxLabel: "用户管理", 
	     	   name: "authorityList", 
	     	   inputValue: "ROLE_AD" 
	     	   },{ 
	     	    boxLabel: "角色管理",
	     	    name: "authorityList",
	     	    inputValue: "ROLE_AC" 
	     	   },  {
	     	    boxLabel: "系统日志", 
	     	    name: "authorityList",
	     	    inputValue: "ROLE_AA"
	     	   }] 
	     	}]
         	
         });
		 var win =Ext.create('Ext.window.Window',{
			title : '添加角色',
			height: 300,
		    width: 800,
		    layout: 'fit',
			modal : true,
			constrain : true,
			resizable :false,
		 	items:formPanel ,
		 	buttons: [{
                    text:'保存',
                    handler:function(){
                    	if(formPanel.form.isValid()){ 
							 formPanel.form.submit({
			                    method:'POST',
								url:url,
								waitMsg:'正在保存，请稍后...',
			                    success: function(form,action) {
			                    	var result =  action.result.infos;
			                    	if(result!='true'){
			                    		alertE(result,2000);
			                    		return ;
			                    	}
			                    	win.close();
									alertI('保存成功！');
									role_store.reload();
			                    },
			                    failure:function(form,action){
			                    	alertE('保存失败！');
			                    }
							 });
                    	}
                    }
                },{
                    text: '取消',
                    handler: function(){
                        win.close();
                    }
                }]
		 });
	    win.show();
	    if(id>0){
	      	url='aRole/update';
	      	win.setTitle('修改用户');
	      	formPanel.form.load({
      		    url : 'aRole/get?roleId='+id,
                waitMsg : '正在载入数据...',
                success : function(form,action,data) {
					var authorityListStore = Ext.decode(action.result.data.authorityListStore);
					formPanel.form.setValues({"authorityList":authorityListStore});
                },
                failure : function(form,action) {
                	alertE('载入信息失败！');
                }
      	});
      }
	},del:function(id){
		var params={'id':id};
		isDoOper(Ext.getCmp('role_grid'),"确定要删除吗？",'aRole/del',params);
	}
};

var role_store = Ext.create('Ext.data.Store', {
     pageSize : pageSize,
     proxy: {
         type: 'ajax',
		 url : 'aRole/list',
         reader: {
             type: 'json',
             root: 'records',
             totalProperty : 'total',
             idProperty : 'id'
         }
     },
      fields: []
 });

Ext.define('admin.view.system.roleMain', {
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
										text : '新增',
										icon : "images/admin/icon_01.png",
										handler:function(){	
											roleMainOper.edit();
										}
									},{
										xtype : 'button',
										style : 'font-size:12px',
										text : '查询',
										icon : "images/admin/icon_04.png",
										handler:function(){	
											role_store.loadPage(1);
										}
									}],
									items : [{
												fieldLabel : '角色名',
												labelWidth :50,
												width : 220,
												id:'s_roleName2',
												xtype : "textfield"
											}]
								}),Ext.create('Ext.grid.Panel', {
									region : "center",
									id:'role_grid',
									extend : 'Ext.grid.Panel',
									border : true,
									loadMask : true,
									store:role_store,
									dockedItems:Ext.create('Ext.toolbar.Paging',{
							    		store : role_store, 
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
									     {text  : '角色名称',dataIndex : 'roleName',width : 200,sortable : true},
							        	 {text : '创建时间',dataIndex : 'createTime',width : 160,sortable : true} ,
							        	 {text : '说明',dataIndex : 'roleDesc',width : 300,sortable : true},
							        	 {header:'操作',width:260,dataIndex:'id',renderer : roleMainOper.renderOp}
							        	 
									    ]
										

									}
								)];
				me.callParent(arguments);
				role_store.on('beforeload', function() {
					role_store.getProxy().extraParams = {
						"roleName" : Ext.getCmp('s_roleName2').getValue()
						};
					 });
				role_store.loadPage(1);
			}
		});
