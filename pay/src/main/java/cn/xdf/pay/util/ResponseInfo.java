package cn.xdf.pay.util;

import java.io.Serializable;

/**
 * 封账接口返回的实体
 * @author liuwei63
 * @param <T>
 */
public class ResponseInfo<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final Integer OK = 200;
    
    public static final Integer ERROR = 500;

    private Integer rtnCode;//状态码
    
    private String rtnMsg;//消息内容
    
    private T data;//数据

    public Integer getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(Integer rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
