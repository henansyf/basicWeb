

var usersMainOper = {
	renderOp:function(v, p, record){
		var str="<a href='javascript:void(0);' onclick='usersMainOper.edit(\""
			+ record.get('id') + "\")'>编辑</a>";	
		var rstate = record.get('isEnabled');
		var isNonLocked = record.get('isAccountNonLocked');
		var statestr = "启用";
		var type=2;
		if (rstate ==true) {
			statestr = "禁用";
			type=1;
		} 
		str += " | <a href='javascript:void(0);'  onclick=usersMainOper.actions('"
				+ record.get('id') + "'," +type + ")>" + statestr + "</a> ";
		str += " | <a href='javascript:void(0);'  onclick=usersMainOper.actions('"
					+ record.get('id') + "',0)>重置密码</a> ";
		if (isNonLocked ==false) {
			str += " | <a href='javascript:void(0);'  onclick=usersMainOper.actions('"
				+ record.get('userId') + "',3)>解锁</a> ";
		}
		str += " | <a href='javascript:void(0);'  onclick=usersMainOper.actions('"
					+ record.get('id') + "',4)>删除</a> ";
		return  str;
	},
	edit: function(id) {
		 var url='aUsers/save';
		 var rolesStore=Ext.create('Ext.data.Store',{
				 proxy: { 
				  		type:'ajax',
					    url: 'aUsers/getRoles',
					    reader:{
					      type: 'json',
					      root: 'result'
					    }
				 },
				 fields : []
			});
		 rolesStore.load();
         var formPanel =Ext.create('Ext.form.Panel',{
                bodyPadding:10, 
                width : 450,
				height : 330,
			    defaultType : 'textfield',
				defaults : {
					labelAlign : 'right',
				    labelWidth :80,
					labelStyle :'font-weight:bold',
				    margin : "(0 0 10 0)",
				    width : 400
			 	},
				items:[{
		                xtype:'hidden',
		                name: 'id'
		            },{
			         name: 'username',
			         regex:/^[a-zA-Z0-9_]{2,18}$/ ,
			         id:'username5',
			         //disabled:true,
	       			 regexText : '登录名由4-18位字母、数字和下划线组成',
	        		 fieldLabel: '<font color="red">*</font>登录名',
	                 allowBlank: false  
			    },{
					fieldLabel : '<font color="red">*</font>姓名',
					name : 'userRealname',
					blankText : '请填写真实姓名',
					allowBlank : false,
					maxLength : 15,
					maxLengthText : '姓名长度不能超过15个字符',
					minLength : 2,
					minLengthText : '姓名长度不能小于2个字符'
				}, {
			        fieldLabel : '手机',
					name : 'userMobileno',
					regexText : '请输入正确的手机号码',
					regex :/^1[34578]\d{9}$/
					
			    },{
					fieldLabel : '邮箱',
					name : 'userEmail',
					regex : /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
					maxLength : 60,
					maxLengthText : '邮箱长度不能超过60个字符',
					regexText : '邮箱格式不正确'
				
				},{
					fieldLabel : '<font color="red">*</font>角色',
					xtype : "combo",
					emptyText : "请选择",
					hiddenName :'roleId',
					name:'roleId',
					store : rolesStore,
					editable : false,
					queryMode : 'local',
					autoScroll : true,
					valueField : 'id',
					allowBlank: false,
					displayField : 'roleName'
				}]
         	
         });
		 var win =Ext.create('Ext.window.Window',{
			title : '添加用户',
			height: 300,
		    width: 450,
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
									user_store.reload();
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
      	url='aUsers/update';
      	win.setTitle('修改用户');
      	formPanel.form.load({
      		    url : 'aUsers/get?id='+id,
                waitMsg : '正在载入数据...',
                success : function(form,action) {
                	Ext.getCmp('username5').setDisabled(true);
                },
                failure : function(form,action) {
                	alertE('载入用户信息失败！');
                }
      	});
      }
	},
	actions:function(id,type){
    	var url='aUsers/resetPWD';
    	var msg = '确认重置吗？';
    	var params={};
    	switch(type){
			case 1:
			 url='aUsers/forbid';
			 msg = '确认禁用吗？';
			 params.isEnabled=false;
			  break;
			case 2:
			 url='aUsers/forbid';
			  params.isEnabled=true;
			 msg = '确认启用吗？';
			  break;
		    case 3:
		     url='aUsers/reOpen';
		     msg = '确认解锁吗？';
		    case 4:
		     url='aUsers/del';
		     msg = '确认删除吗？';
		     break;
			default:
			  break;
		}
    	var mgrid =Ext.getCmp('user_grid');
    	params.id=id;
		isDoOper(Ext.getCmp('user_grid'),msg,url,params);
	}
};

var user_store = Ext.create('Ext.data.Store', {
     pageSize : pageSize,
     proxy: {
         type: 'ajax',
		 url : 'aUsers/list',
         reader: {
             type: 'json',
             root: 'records',
             totalProperty : 'total',
             idProperty : 'id'
         }
     },
      fields: []
 });

Ext.define('admin.view.system.usersMain', {
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
											usersMainOper.edit();
										}
									},{
										xtype : 'button',
										style : 'font-size:12px',
										text : '查询',
										icon : "images/admin/icon_04.png",
										handler:function(){	
											user_store.loadPage(1);
										}
									}],
									items : [{
												fieldLabel : '账号',
												labelWidth :50,
												width : 180,
												id:'s_username',
												xtype : "textfield"
											},{
												fieldLabel : '用户姓名',
												labelWidth :80,
												width : 180,
												id:'s_userRealname',
												xtype : "textfield"
											}]
								}),Ext.create('Ext.grid.Panel', {
									region : "center",
									extend : 'Ext.grid.Panel',
									border : true,
									id:'user_grid',
									loadMask : true,
									store:user_store,
									dockedItems:Ext.create('Ext.toolbar.Paging',{
							    		store : user_store, 
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
									    {header:'账号',dataIndex:'username',sortable:true},
									    {header:'用户姓名',dataIndex:'userRealname',sortable:true},
									    {header:'手机号',dataIndex:'userMobileno',sortable:true},
									    {header:'角色',dataIndex:'roleName',sortable:true},
									   // {header:'用户类型',dataIndex:'userType',sortable:true,renderer:function(val){if(val==1){return '管理员';}else if(val==2){return '操作员';}}},
									    {header:'创建时间',dataIndex:'createdDa',width:180,sortable:true},
									    {header:'用户状态',dataIndex:'isEnabled',sortable:true,renderer:function(val){if(val==1){return '启用';}else{return '禁用';}}},
									    {header:'操作',width:260,dataIndex:'id',renderer : usersMainOper.renderOp}
									    ]
										

									}
								)];
				me.callParent(arguments);
				user_store.on('beforeload', function() {
					user_store.getProxy().extraParams = {
						"username" : Ext.getCmp('s_username').getValue(),
						"userRealname":Ext.getCmp('s_userRealname').getValue()
						};
				 });
				user_store.loadPage(1);
			}
		});
