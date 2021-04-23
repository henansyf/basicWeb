var pageSize =5;
var username = document.getElementById('username').value;
var security = document.getElementById('security').value;
var userId  = document.getElementById('userId').value;

function alertE(info,time){
	Ext.Msg.show({
    title: '操作提示 ',
    icon: Ext.MessageBox.ERROR,
    closable:false,
    msg: info
  });
  if(time==null){time=3000;}
  setTimeout(function () {
           Ext.Msg.hide();
    },time);
}

function alertI(info,time){
	Ext.Msg.show({
    title: '操作提示 ',
    closable:false,
    msg: info
  });
  if(time==null){time=1000;}
  setTimeout(function () {
           Ext.Msg.hide();
    },time);
}

function setVDtrue(vdid){
	var cmpp=Ext.getCmp(vdid);
	cmpp.setVisible(true);
	cmpp.setDisabled(false);
}
function setVDfalse(vdid){
	var cmpp=Ext.getCmp(vdid);
	cmpp.setVisible(false);
	cmpp.setDisabled(true);
}





//确认执行操作吗
function isDoOper(grid,msg,url,params,disabledId){
	Ext.MessageBox.confirm('确认', msg,
	function(btn) {
		if (btn == 'yes') {
			var par=Ext.MessageBox.wait("正在努力处理...");
			Ext.Ajax.request({
						url : url,
						timeout:300000,
						success : function(response, options) {
							par.hide();
							var o = Ext.decode(response.responseText);
							if (o.infos == 'true') {
							  alertI("操作成功！");
							  if(disabledId!=null){
							  	  Ext.getCmp(disabledId).setDisabled(true);
							  }
							  grid.getStore().reload();
							}else if(o.infos == 'false'){
								alertE("操作失败！");
							}else{
								alertE(o.infos);
							}
						},
						failure : function() {
							par.hide();
							alertE("操作失败！");
						},
						params : params
					});
					}
				});
}



