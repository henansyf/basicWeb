Ext.define("admin.store.MenuStore", {
			extend : "Ext.data.TreeStore",
			proxy : {
				type : "ajax", // 获取方式
				url : "alogin/getUrl?action=node" // 获取树节点的地址
			},
			clearOnLoad : true,
			fields : [{name : "id",type : "string"},
			{name : "text",type : "string"},
			{name : "iconCls",type : "string"},
			{name : "leaf",type : "boolean"},
			{name : 'type'},
			{name : 'component'}],
			nodeParam : "id"// 设置传递给后台的参数名,值是树节点的id属性
});
