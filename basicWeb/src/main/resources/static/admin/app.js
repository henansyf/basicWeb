Ext.Loader.setConfig({
	enabled : true
});//开启动态加载

Ext.Loader.setPath({
	'Ext.ux' : 'ext5/ux',
	'ueditor':'ueditor'//设置需要的包路径
});
Ext.override(Ext.form.field.File, {
    extractFileInput: function() {
        var me = this,
            fileInput = me.fileInputEl.dom,
            clone = fileInput.cloneNode(true);

        fileInput.parentNode.replaceChild(clone, fileInput);
        me.fileInputEl = Ext.get(clone);

        me.fileInputEl.on({
            scope: me,
            change: me.onFileChange
        });

        return fileInput;
}});




Ext.application({
    name: 'admin',//为应用程序起一个名字,相当于命名空间
    appFolder : 'admin',
    autoCreateViewport : true
});


function updatePass(){
	var formPanel =Ext.create('Ext.form.FormPanel',{
        width: 330,
        bodyPadding: 10, 
        defaultType: 'textfield',
        items: [{
                fieldLabel: '旧密码',
                name: 'password',
                regex :/[a-zA-Z0-9]{4,16}/,
				regexText : '必须是4~16位的字母数字组成',
                allowBlank:false,
                inputType:'password',
                blankText:'旧密码不能为空！'
            },{
                fieldLabel: '新密码',
                name: 'newPassword',
                id: 'newPassword',
                regex :/[a-zA-Z0-9]{4,16}/,
				regexText : '必须是4~16位的字母数字组成',
                allowBlank:false,
                inputType:'password',
                blankText:'新密码不能为空！'
            },{
                fieldLabel: '密码确认',
                name: 'newPwd',
                id:'newPwd',
                regex :/[a-zA-Z0-9]{4,16}/,
				regexText : '必须是4~16位的字母数字组成',
                allowBlank:false,
                inputType:'password',
                blankText:'密码确认不能为空！'
            }
        ]
    });
   var win = Ext.create('Ext.window.Window', {
                title:'修改密码',
                width:330,
                height:180,
                items:formPanel ,
                modal :true,
                buttons: [{
                    text:'保存',
                    handler:function(){
                    	if(formPanel.form.isValid()){ 
                    		 if(Ext.getCmp('newPassword').getValue()!=Ext.getCmp('newPwd').getValue()){
                    		 	Ext.MessageBox.alert('提示','两次新密码输入不一致！' );
                    		 	return;
                    		 }
							 formPanel.form.submit({
			                    method:'POST',
								url:'alogin/updatePw.do',
			                    success: function(form,action) {
			                    	var result = action.result.infos;
			                    	if(result=='0'){
			                    		win.close();
			                    		Ext.MessageBox.show({  
                    			               title:"提示",  
                    			               msg:"修改密码成功！3s后系统将自动跳到登录页面！",  
                    			               progress:true,  
                    			               width:300,  
                    			               wait:true,  
                    			               waitConfig:{interval:600}//0.6s进度条自动加载一定长度  
			                    		});  
			                    	   setTimeout(function(){
				                    	   Ext.MessageBox.hide();
				                    	    window.location.href =document.getElementById("basePath").value+"admin.jsp";
				                    	   },3000); 
			                    	    
			                    	}else if(result=='1'){
			                    		Ext.MessageBox.alert('提示','旧密码输入错误！' );
			                    	}else if(result=='2'){
			                    		Ext.MessageBox.alert('提示','两次新密码输入不一致！' );
			                    	}
			                    	
			                    },
			                    failure:function(form,action){
			                    	Ext.MessageBox.alert('提示', '修改密码失败！');
			                    }
							 });
                    	}
                    }
                },{
                    text: '返回',
                    handler: function(){
                        win.close();
                    }
                }]
            });
      win.show();
}