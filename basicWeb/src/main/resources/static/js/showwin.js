/**
 * 弹出窗口（请稍等````） 用法：1.将该js引入jsp中。2. onClick="sAlert('要显示的内容')"
 * @param {} str
 */
var bgObj; 
var msgObj;
var title;
function sAlert(str) {
		var msgw, msgh, bordercolor;
		msgw = 400;//提示窗口的宽度
		msgh = 120;//提示窗口的高度
		titleheight = 25//提示窗口标题高度
		bordercolor = "#336699";//提示窗口的边框颜色
		titlecolor = "#99CCFF";//提示窗口的标题颜色
		var sWidth, sHeight;
		sWidth = document.body.offsetWidth;//浏览器工作区域内页面宽度 或使用 screen.width//屏幕的宽度
		sHeight = screen.height;//屏幕高度（垂直分辨率）
		//背景层（大小与窗口有效区域相同，即当弹出对话框时，背景显示为放射状透明灰色）
		bgObj = document.createElement("div");//创建一个div对象（背景层） //动态创建元素，这里创建的是 div
		//定义div属性，即相当于(相当于，但确不是，必须对对象属性进行定义
		//<div id="bgDiv" style="position:absolute; top:0; background-color:#777; filter:progid:DXImagesTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75); opacity:0.6; left:0; width:918px; height:768px; z-index:10000;">__tag_67$224_
		bgObj.setAttribute('id', 'bgDiv');
		bgObj.style.position = "absolute";
		bgObj.style.top = "0";
		bgObj.style.background = "#777";
		bgObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
		bgObj.style.opacity = "0.6";
		bgObj.style.left = "0";
		bgObj.style.width = sWidth + "px";
		bgObj.style.height = sHeight + "px";
		bgObj.style.zIndex = "10000";
		document.body.appendChild(bgObj);//在body内添加该div对象
		
		//创建一个div对象（提示框层）
		 msgObj = document.createElement("div")
		//定义div属性，即相当于
		//<div id="msgDiv" align="center" style="background-color:white; border:1px solid #336699; position:absolute; left:50%; top:50%; font:12px/1.6em Verdana,Geneva,Arial,Helvetica,sans-serif; margin-left:-225px; margin-top:npx; width:400px; height:100px; text-align:center; line-height:25px; z-index:100001;">__tag_82$305_
		msgObj.setAttribute("id", "msgDiv");
		msgObj.setAttribute("align", "center");
		msgObj.style.background = "white";
		msgObj.style.border = "1px solid " + bordercolor;
		msgObj.style.position = "absolute";
		msgObj.style.left = "50%";
		msgObj.style.top = "50%";
		//msgObj.style.top = (document.documentElement.scrollTop + (sHeight - msgh) / 2) + "px";  
	    //msgObj.style.left = (sWidth - msgw) / 2 + "px";
		msgObj.style.font = "12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
		msgObj.style.marginLeft = "-225px";
		msgObj.style.marginTop = document.documentElement.scrollTop-130 + "px";
		msgObj.style.width = msgw + "px";
		msgObj.style.height = msgh + "px";
		msgObj.style.textAlign = "center";
		msgObj.style.lineHeight = "25px";
		msgObj.style.zIndex = "10001";
	    title = document.createElement("h4");//创建一个h4对象（提示框标题栏）
		//定义h4的属性，即相当于
		//<h4 id="msgTitle" align="right" style="margin:0; padding:3px; background-color:#336699; filter:progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100); opacity:0.75; border:1px solid #336699; height:18px; font:12px Verdana,Geneva,Arial,Helvetica,sans-serif; color:white; cursor:pointer;" onclick="">关闭__tag_100$372_
		title.setAttribute("id", "msgTitle");
		title.setAttribute("align", "center");
		title.style.margin = "0";
		title.style.padding = "3px";
		title.style.background = bordercolor;
		title.style.filter = "progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
		title.style.opacity = "0.75";
		title.style.border = "1px solid " + bordercolor;
		title.style.height = "18px";
		title.style.font = "12px Verdana, Geneva, Arial, Helvetica, sans-serif";
		title.style.color = "white";
		//title.style.cursor = "pointer";
		title.innerHTML = "在系统完成处理之前，请不要主动刷新页面";
		//title.onclick = removeObj;
//		var button = document.createElement("input");//创建一个input对象（提示框按钮）
		//定义input的属性，即相当于
		//__tag_117$2_
//		button.setAttribute("type", "button");
//		button.setAttribute("value", "关闭");
//		button.style.width = "60px";
//		button.style.align = "center";
//		button.style.marginLeft = "250px";
//		button.style.marginBottom = "10px";
//		button.style.background = bordercolor;
//		button.style.border = "1px solid " + bordercolor;
//		button.style.color = "white";
//		button.onclick = removeObj;
		function removeObj() {//点击标题栏触发的事件
			document.body.removeChild(bgObj);//删除背景层Div
			document.getElementById("msgDiv").removeChild(title);//删除提示框的标题栏
			document.body.removeChild(msgObj);//删除提示框层
		}
		document.body.appendChild(msgObj);//在body内添加提示框div对象msgObj
		document.getElementById("msgDiv").appendChild(title);//在提示框div中添加标题栏对象title
		var txt = document.createElement("p");//创建一个p对象（提示框提示信息）
		//定义p的属性，即相当于
		//__tag_137$2_测试效果__tag_137$43_
		txt.style.margin = "1em 0";
		txt.setAttribute("id", "msgTxt");
		txt.setAttribute("align", "left");
		var nrwstr = '<table><tr><td><span style="margin-left: 10px;margin-right: 30px;"><img src="images/admin/working.gif"/>'+
			'</span></td><td>'+str+'</td></div></tr></table>';
		txt.innerHTML = nrwstr;//来源于函数调用时的参数值
		document.getElementById("msgDiv").appendChild(txt);//在提示框div中添加提示信息对象txt
	//	document.getElementById("msgDiv").appendChild(button);//在提示框div中添加按钮对象button
	}
	
function removeAlert() {//点击标题栏触发的事件
		document.body.removeChild(bgObj);//删除背景层Div
		document.getElementById("msgDiv").removeChild(title);//删除提示框的标题栏
		document.body.removeChild(msgObj);//删除提示框层
}

/*//屏蔽F5  
document.onkeydown = mykeydown;  
function mykeydown() {  
	if (event.keyCode == 116) //屏蔽F5刷新键     
	{  
	    window.event.keyCode = 0;  
	    return false;  
	}  
}*/