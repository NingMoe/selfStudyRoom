package cn.xdf.pay.exception;

/**
 * 自定义异常
 * @author liuwei63
 * @version 1.0.0
 * @date 18/4/11 上午10:50.
 */
public class MyException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -35345762680637289L;

	public MyException(String message) {
        super(message);
    }

}
