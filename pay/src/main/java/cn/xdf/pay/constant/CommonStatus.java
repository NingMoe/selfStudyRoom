package cn.xdf.pay.constant;

public class CommonStatus {
	
	public  static final int OK=200;//操作成功
	
	public  static final int EXCEPTION=500;//服务器内部异常
	
	public  static final int FORBIDDEN=403;//拒绝服务(参数验证不通过)
	
	public  static final int UNAUTHORIZED=401;//调用系统权限不足(身份认证不通过)
}
