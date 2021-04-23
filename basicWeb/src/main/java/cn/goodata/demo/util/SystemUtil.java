package cn.goodata.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.Random;


public class SystemUtil {
	public static Random random = new Random();
	/**
	 * 方法描述:取得request的请求
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	/**
	 * 方法描述:取得session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	
	public static String  objectToString(Object obj,boolean allowEmpty){
		if(obj==null){
			if(allowEmpty){
				return "";
			}
			return null;
		}else if(obj.toString().equals("null")){
			return null;
		}else{
			return obj.toString();
		}
	}
	
	
	
	/*public static Users getUser() {
		Users userDetails = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object object = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			if (object instanceof Users) {
				userDetails = (Users) object;
			}
		}
		return userDetails;
	}*/
	// 获取项目路径
	public static String getSystemPath() {
		return getSession().getServletContext().getRealPath("/");
	}
	/*public static String getUserFilePath() {
		return getSystemPath() + getExportFilePath();
	}*/
	/*public static String getExportFilePath(){
		return  "export_files/"
				+ getUser().getUsername() + "/";
	}*/
	
	public static String getAttachmentPth(){
		String realPath =getSystemPath();
		return realPath.substring(0,realPath.lastIndexOf("webapps")+8);//获取物理路径
	}
	
	
	
	
	
	//获取文件名:毫秒数+三位随机数
	/*public static String getRandomFileName(){
		Random rand=new Random(); 
		return System.currentTimeMillis()+rand.nextInt(10000)+"";
	}
	
   public static String makeArrayToString(String[] array){
	  String s="";
	  for(String str:array){
		  s+=str+",";
	  }
	  if(s.length()>0){
		  s.substring(0,s.length()-1);
	  }
	  return s;
   }*/

   

      
      
      
}
