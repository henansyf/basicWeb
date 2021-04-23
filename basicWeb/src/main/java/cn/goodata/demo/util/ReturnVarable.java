package cn.goodata.demo.util;



public  class ReturnVarable {
	/**
	 * 方法描述:ext返回参数 
	 * @param:result true表示成功，false表示失败
	 * @param:info  返回信息
	 * return:返回失败json字符串
	 * */
	public static String extSuccessInfo(boolean result,String info){
		StringBuffer sb = new StringBuffer();
		sb.append("{success:").append(result).append(",");
		sb.append("infos:'").append(info).append("'");
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 方法描述:ext返回参数 
	 * @param:result true表示成功，false表示失败
	 * @param:info  返回信息
	 * return:返回失败json字符串
	 * */
	public static String extFailureInfo(boolean result,String info){
		StringBuffer sb = new StringBuffer();
		sb.append("{Failure:").append(result).append(",");
		sb.append("infos:'").append(info).append("'");
		sb.append("}");
		return sb.toString();
	}
	
}
